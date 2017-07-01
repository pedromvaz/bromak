package com.bromakgame

import com.bromakgame.users.*
import com.bromakgame.worlds.*
import com.bromakgame.creatures.*
import com.bromakgame.learning.*
import com.bromakgame.quests.*

import com.bromakgame.creatures.Champion

class BootStrap {

    def init = {
		
		// -----------------------
		// Roles and Administrator
		// -----------------------
		
        def adminRole = new Role(authority: 'ROLE_ADMIN', description: 'An administrator of the bromak website').save()
        def adminUser = new User(username: 'admin', email: 'admin@bromakgame.com', password: 'admin', enabled: true).save()

        UserRole.create adminUser, adminRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }

        assert User.count() == 1
        assert Role.count() == 1
        assert UserRole.count() == 1
		
		// ---------------------------------------------------------------
		// Creature Races (name, desc, intelligent, enabled, starting pop)
		// ---------------------------------------------------------------
		
		// intelligent ones
		def humanRace	= addRace('Human',	'The Human race.',		true, true, 20)
		def dwarfRace	= addRace('Dwarf',	'The Dwarven race.',	true, true, 15)
		def elfRace		= addRace('Elf',	'The Elven race.',		true, true, 10)
		def orcRace		= addRace('Orc',	'The Orchish race.',	true, true, 25)
		def goblinRace	= addRace('Goblin',	'The Goblin race.',		true, true, 30)
		def trollRace	= addRace('Troll',	'The Troll race.',		true, false, 5)
		
		// savage ones
		def wolfRace	= addRace('Wolf',	'The Wolf race.',		false, true, 15)
		def bearRace	= addRace('Bear',	'The Bear race.',		false, true, 10)
		def eagleRace	= addRace('Eagle',	'The Eagle race.',		false, true, 5)
		
		assert Race.count() == 9
		
		// ------
		// Player
		// ------

		def playerRole = new Role(authority: 'ROLE_PLAYER', description: 'A player on the bromak website').save()
		def playerUser = new User(username: 'player', email: 'player@bromakgame.com', password: 'player', enabled: true).save()
		def pedroUser = new User(username: 'pedro', email: 'pedro@bromakgame.com', password: 'pedro', enabled: true).save()

        UserRole.create playerUser, playerRole
		UserRole.create pedroUser, playerRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }

        assert User.count() == 3
        assert Role.count() == 2
        assert UserRole.count() == 3
		
		// ------
		// Skills
		// ------
		
		// hunting
		def bashing = new Skill(
			name: 'Bashing',
			description: 'Using hard objects as weapons, like branches and rocks').save()
		def tracking = new Skill(
			name: 'Tracking',
			description: 'Tracking animals by analysing footprints, blood stains and smells').save()
		def throwing = new Skill(
			name: 'Throwing',
			description: 'Throwing weapons like stones and spears').save()
		def stealth = new Skill(
			name: 'Stealth',
			description: 'Moving silently and using surrounding objects as cover').save()
		// stone carving
		def stoneCarving = new Skill(
			name: 'Stone Carving',
			description: 'The carving of stone weapons and tools.').save()
		// basic clothing
		def leatherworking = new Skill(
			name: 'Leatherworking',
			description: 'Skinning the leather from animals to make basic clothing.').save()
		// cave painting
		def cavePainting = new Skill(
			name: 'Cave Painting',
			description: 'Painting deeds in cave walls.').save()
		
		def agriculture = new Skill(
			name: 'Agriculture',
			description: 'Agriculture').save()
		def masonry = new Skill(
			name: 'Masonry',
			description: 'Masonry').save()
		def pottery = new Skill(
			name: 'Pottery',
			description: 'Pottery').save()
		
		assert Skill.count() == 10
		
		// ------
		// Epochs
		// ------
		
		def nomadic = new Epoch(
			name: 'Ancient Nomadic Era',
			description: 'The start of the Ancient Era, where Nomadic peoples were common.')
		.addToSkills(bashing)
		.addToSkills(tracking)
		.addToSkills(throwing)
		.addToSkills(stealth)
		.addToSkills(stoneCarving)
		.addToSkills(leatherworking)
		.addToSkills(cavePainting)
		.save()
		
		def sedentary = new Epoch(
			name: 'Ancient Sedentary Era',
			description: 'The end of the Ancient Era, where Nomadic peoples became Sedentary.')
		.addToSkills(agriculture)
		.addToSkills(masonry)
		.addToSkills(pottery)
		.save()
		
		assert Epoch.count() == 2
		
		// -----------
		// Skill Trees
		// -----------
		
		for (race in Race.findAllByIntelligent(true)) {
			for (skill in nomadic.skills) {
				race.skillTree.addToSkills(skill)
			}
			race.save()
		}
		
		// ----------------
		// Skill Categories
		// ----------------
		
		def combat = new SkillCategory(
			name: 'Combat',
			description: 'Various forms of combat')
		.addToSkills(bashing)
		.addToSkills(throwing)
		.save()
		
		def stealthCategory = new SkillCategory(
			name: 'Stealth',
			description: 'Moving silently and using surrounding objects as cover')
		.addToSkills(stealth)
		.save()
		
		def trackingCategory = new SkillCategory(
			name: 'Tracking',
			description: 'Tracking animals by analysing footprints, blood stains and smells')
		.addToSkills(tracking)
		.save()
		
		assert SkillCategory.count() == 3
		
		// ---------------
		// World & Regions
		// ---------------
		
		def worldRadius = 2
		def world = new World(name: "Bromak", radius: worldRadius, maxNumPlayers: 10)
		world.owner = adminUser
		
		world.generate()
		
		world.save(flush:true)
		
		assert World.count() == 1
		assert Region.count() == numRegionsByRadius(worldRadius)
		assert Area.count() == numRegionsByRadius(worldRadius) * 7
		
		// -------------------------
		// Champions and Family Tree
		// -------------------------
		
		def elrond = new Champion(firstName: 'Elrond', gender: 'm', race: elfRace,
			user: playerUser, world: world).save()
		def celebrian = new Champion(firstName: 'Celebrian', gender: 'f', race: elfRace,
			user: playerUser, world: world).save()
		def elladan = new Champion(firstName: 'Elladan', gender: 'm', race: elfRace,
			user: playerUser, world: world).save()
		def elrohir = new Champion(firstName: 'Elrohir', gender: 'm', race: elfRace,
			user: playerUser, world: world).save()
		def arwen = new Champion(firstName: 'Arwen', gender: 'f', race: elfRace,
			user: playerUser, world: world).save()
		
		assert Champion.count() == 5
		
		for (child in [elladan, elrohir, arwen]) {
			child.father = elrond
			child.mother = celebrian
		}
		
		// ---------
		// Community
		// ---------
		
		def community = new Community().save()
		
		for (champion in [elrond, celebrian, elladan, elrohir, arwen]) {
			community.addToCreatures(champion)
		}
		
		assert Community.count() == 1
		
		// -----------
		// Quest Types
		// -----------
		
		def terrainScouting = new QuestType(name: 'Terrain Scouting',
			description: 'Scouting new terrain will provide information ' +
				'on its wild life, plants, minerals, soil, among other things.',
			groupCap: 3).save()
		def animalHunting = new QuestType(name: 'Animal Hunting',
			description: 'Your first quest will be to hunt a deer with some members from your community.',
			groupCap: 4).save()
		def animalSkinning = new QuestType(name: 'Animal Skinning',
			description: 'Your community quickly felt the need to find something sharp ' +
				'with which to cut through animal skin.',
			groupCap: 3).save()
		def winterIsComing = new QuestType(name: 'Winter is coming',
			description: 'Your community must learn to skin the animals they hunt, for clothing, ' +
				'otherwise they won\'t last the winter.',
			groupCap: 6).save()
		def toArms = new QuestType(name: 'To Arms!',
			description: 'Having suffered an attack from a sabertooth, the community feels threatened.',
			groupCap: 6).save()
		def fire = new QuestType(name: 'Fire!',
			description: 'The thunderstorm left behind a threat, one that could be used for the community\'s benefit.',
			groupCap: 3).save()
		
		assert QuestType.count() == 6
	}
	
	private Race addRace(String name, String description, boolean intelligent, boolean enabled, int startingPopulation) {
		Race race = new Race(name: name, description: description,
			intelligent: intelligent, enabled: enabled,
			startingPopulation: startingPopulation)
		race.skillTree = new SkillTree()
		race.save()
		
		return race
	}

	private int numRegionsByRadius(int radius) {
		if (radius == 0) {
			return 1
		} else {
			return radius * 6 + numRegionsByRadius(radius - 1)
		}
	}

	def destroy = {
	}
}
