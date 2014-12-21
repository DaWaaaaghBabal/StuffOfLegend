/**
 * 
 */
var addedNumber={}
function addCircleChoice (node) {
	var circleChoiceElement = document.getElementById("circleChoiceTemplate").cloneNode(true);
	if(node in addedNumber) {
		addedNumber[node]++;
	} else {
		addedNumber[node] = 1;
	}
	circleChoiceElement.id = node.id + "." + addedNumber[node];
	circleChoiceElement.style.display="block";
	node.appendChild(circleChoiceElement);
}

function addAlternate (node) {
	var alternateElement = document.getElementById("alternateTemplate").cloneNode(true);
	if(node in addedNumber) {
		addedNumber[node]++;
	} else {
		addedNumber[node] = 1;
	}
	alternateElement.id = node.id + "." + addedNumber[node];
	alternateElement.style.display="block";
	node.appendChild(alternateElement);
}

function collapse(node) {
	var contentNode = node.querySelector(".circleContent");
	var button = node.querySelector(".collapseButton");
	var display = contentNode.style.display;
	if (display=="block") {
		contentNode.style.display = "none";
		button.className="expand";
	} else if (display=="none") {
		contentNode.style.display = "block";
		button.className="collapse";
	}
}