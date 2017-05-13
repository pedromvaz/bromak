/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var rand = function(limit) {
	return Math.floor(Math.random()*limit);
};

function createRandomWord() {
	var formats = "CCV CVC CVV VCC VCV VVC";
	formats += " CCVC CCVV CVCC CVCV CVVC";
	formats += " VCCV VCVC VCVV VVCC VVCV";
	formats = formats.split(" ");

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
