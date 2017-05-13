/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var rand = function(limit) {
	return Math.floor(Math.random()*limit);
};

function createRandomWord() {
	var formats = ["CCV", "CVC", "CVV", "VCC", "VCV", "VVC"];
	formats.push("CCVC");
	formats.push("CCVV");
	formats.push("CVCC");
	formats.push("CVCV");
	formats.push("CVVC");
	formats.push("VCCV");
	formats.push("VCVC");
	formats.push("VCVV");
	formats.push("VVCC");
	formats.push("VVCV");

	return createRandomWordWithFormat(formats[rand(formats.length)]);
}

function createRandomWordWithFormat(format) {
	var consonants = 'bcdfghjklmnpqrstvwxyz'.split('');
	var vowels = 'aeiou'.split('');
	var word = '';

	for (var i = 0; i < format.length; i++) {
		var randLetter = (format[i] === "C") ? consonants[rand(consonants.length)] : vowels[rand(vowels.length)];

		word += (i === 0) ? randLetter.toUpperCase() : randLetter;
	}

	return word;
}
