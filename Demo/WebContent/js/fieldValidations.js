function formValidation() {

	var status = true;

	document.getElementById("nameError").innerHTML = "";
	document.getElementById("midError").innerHTML = "";
	document.getElementById("pracError").innerHTML = "";
	document.getElementById("competencyError").innerHTML = "";
	document.getElementById("verError").innerHTML = "";
	var name = document.getElementById("ename");
	var mid = document.getElementById("mid");
	var prac = document.getElementById("practice");
	var competency = document.getElementById("competency");
	var vertical = document.getElementById("vertical");
	var valueMid = /^[M|m][0-9]{7}$/;
	var letters = /^[A-Za-z ]+$/;
	
	if(!letters.test(name.value)) {
		name.focus();
		document.getElementById("nameError").innerHTML = "Please enter valid name";
		status = false;
	} else if(name.value.length > 25) {
		name.focus();
		document.getElementById("nameError").innerHTML = "Please enter name upto 25 characters only";
		status = false;
	}
	if (!mid.value.match(valueMid)) {
		mid.focus();
		document.getElementById("midError").innerHTML = "Wrong MID Format";
		status = false;
	}
	if(!letters.test(prac.value)) {
		prac.focus();
		document.getElementById("pracError").innerHTML = "Please enter valid practice";
		status = false;
	} else if(prac.value.length > 25) {
		prac.focus();
		document.getElementById("pracError").innerHTML = "Please enter practice upto 25 characters only";
		status = false;
	}
	if (competency.value == "Default") {
		competency.focus();
		document.getElementById("competencyError").innerHTML = "Please select Competency";
		status = false;
	}
	if(!letters.test(vertical.value)) {
		vertical.focus();
		document.getElementById("verError").innerHTML = "Please enter valid vertical";
		status = false;
	} else if(vertical.value.length > 25) {
		vertical.focus();
		document.getElementById("verError").innerHTML = "Please enter vertical upto 25 characters only";
		status = false;
	}
	return status;

}

function resetFields() {
	
	document.getElementById("nameError").innerHTML = "";
	document.getElementById("midError").innerHTML = "";
	document.getElementById("pracError").innerHTML = "";
	document.getElementById("competencyError").innerHTML = "";
	document.getElementById("verError").innerHTML = "";
	document.getElementById("msg").innerHTML = "";
	
}