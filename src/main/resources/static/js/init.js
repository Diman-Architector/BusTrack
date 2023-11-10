let xhr = new XMLHttpRequest(); //создаем объект xhr в классе XMlHttpRequest. В этом классе у нас содержатся методы для
//работы с сетью Http протоколами

/* Параметры карты */
let lat = null;
let lng = null;

/* Модальное окно загрузки */
const modalUpload = new bootstrap.Modal(document.getElementById('modalUpload'), {});
let btnUpload = document.getElementById("btnUpload");
let btnUploadPerform = document.getElementById("btnUploadPerform");
let upload = document.getElementById("upload");

/*Модальное окно подтверждения*/
const modalConfirm = new bootstrap.Modal(document.getElementById('modalConfirm'), {}) //получаем модальное окно подтверждение
let btnYes = document.getElementById("btnYes");
let btnNo = document.getElementById("btnNo");
btnNo.addEventListener("click", (e) => { modalConfirm.hide(); }); //при нажатии на кнопку модального окна "закрыть" окно скроется

/*Кнопки остановок*/
let selectedStationId = null;
const modalStation = new bootstrap.Modal(document.getElementById('modalStation'), {}) //exampleModal берем из кода
//модального окна в admin.html - мы его добавили из bootstrap
let btnEditeStation = document.getElementById("btnEditeStation"); //переменная для кнопки редактирования
let btnDeleteStation = document.getElementById("btnDeleteStation"); //переменная для кнопки удаления
let btnCreateStation = document.getElementById("btnCreateStation"); //переменную с id, который мы приписали в html коде
// в admin.html
let baseUl = document.getElementById("listItemsStation"); //объявляем переменную в которую будет приходить значение
//названия остановки и передаваться на страницу admin.html



function clearSelection(list) {
    Array.prototype.forEach.call(list, child=>{
        child.classList.remove("li-selected");
    }); //функция для снятия выделения с выбранного поля в таблице
}

function buttonActivate(b1, b2, status){
    b1.disabled = status;
    b2.disabled = status;
}// функция для снятия выделия с кнопки


