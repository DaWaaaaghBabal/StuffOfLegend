/**
 * 
 */
var addedNumber={}

function create(tagName){
	return document.createElement(tagName);
}

function addCircleChoice (nodeId) {
	var node = document.getElementById(nodeId)
	if(nodeId in addedNumber) {
		addedNumber[nodeId]++;
	} else {
		addedNumber[nodeId] = 1;
	}
	var count = addedNumber[nodeId] ;
	var id = nodeId + "." + count;

	var container = create("div");
	container.id = id;

	var header = create ("div");
	var title = create ("label");
	title.innerHTML = "Ability " + count;
	var button = create ("label");
	button.innerHTML = " (add choice)";
	button.id = id + ".add";
	button.onclick = function() {
		addAlternate(id); 
	};
	header.appendChild(title);
	header.appendChild(button);
	container.appendChild(header);
	
	var content = create("div");
	container.appendChild(content);
	
	node.appendChild(container);
	addAlternate(id);
}

function addAlternate (nodeId) {;
	var node = document.getElementById(nodeId)
	if(nodeId in addedNumber) {
		addedNumber[nodeId]++;
	} else {
		addedNumber[nodeId] = 1;
	}
	id = nodeId + "." + addedNumber[nodeId];
	var container = create("div");
	var nameInput = create("input");
	nameInput.type ="text";
	nameInput.name = id + ".name";
	var textInput = create("input");
	textInput.type ="text";
	textInput.name = id + ".text";
	var typeInput = create("input");
	typeInput.type ="text";
	typeInput.name = id + ".type";
	typeInput.list="types";
	container.appendChild(nameInput);
	container.appendChild(textInput);
	container.appendChild(typeInput);
	node.appendChild(container);
}