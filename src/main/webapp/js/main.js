/**
 * 
 */
function validate() {
	var send_addr = validateAdressPassw("first_tr", "incorrect_sender",
			"sender_id");
	var send_pass = validateAdressPassw("second_tr", "incorrect_password",
			"password_id");
	var receiv_addr = validateAdressPassw("third_tr", "incorrect_receiver",
		"receiver_id");
	var subj=validateSubjBody("fourth_tr","incorrect_subject","subject_id");
	var body=validateSubjBody("fifth_tr","incorrect_body","body_id");
	if (!send_addr || !send_pass || !receiv_addr || !subj || !body)
		return false;
	else
		return true;
}

function validateAdressPassw(id_line, id_column, id_field) {
	var messege = "";
	var field_value = document.getElementById(id_field).value;
	if (isNotMatch(field_value)) {
		if (isBlank(field_value)) {
			messege = "Поле должно быть заполнено";
		} else
			messege = "Можно вводить только латинские буквы и цифры";
		createMessage(id_column, id_line,messege)
		return false;
	}
	else {
		deleteMessegeIfExist(id_column, id_line);
	return true;
	}

}

function isNotMatch(field_value) {
	return !field_value.match((/^[a-zA-Z0-9]+$/));

}
function isBlank(field_value) {
	if (field_value == "" || field_value.trim() == "")
		return true;
	else
		return false;

}

function createMessage(id_column, id_line,messege) {
	deleteMessegeIfExist(id_column, id_line);
	var parent = document.getElementById(id_line);
	var td = '<td id="' + id_column + '">' + messege + '</td>';
	parent.innerHTML += td;

}
function deleteMessegeIfExist(id_column, id_line){
	var mes = document.getElementById(id_column);
	var parent = document.getElementById(id_line);
	if (mes != undefined) {
		parent.removeChild(mes);
}
}

function validateSubjBody( id_line,id_column,id_field){
	var field_value = document.getElementById(id_field).value;
	if (isBlank(field_value)){
		messege="Поле должно быть заполнено";
		createMessage(id_column, id_line,messege);
		return false;
	}
	else {
		deleteMessegeIfExist(id_column, id_line);
		return true;
	}
	
}
