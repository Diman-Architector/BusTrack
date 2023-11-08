/* Авторизация*/
const modalLog = new bootstrap.Modal(document.getElementById('modalLog'), {}) //modalLog берем из кода
//модального окна в log.html - мы его добавили из bootstrap
const modalReg = new bootstrap.Modal(document.getElementById('modalReg'), {}) //modalLog берем из кода
//модального окна в reg.html - мы его добавили из bootstrap

let xhr = new XMLHttpRequest(); //создаем объект xhr в классе XMlHttpRequest. В этом классе у нас содержатся методы для
//работы с сетью Http протоколами

let btnLog = document.getElementById("btnLog"); //переменная для кнопки btnLog из файла index.html
btnLog.addEventListener ("click", function() { //прописываем слушатель на клик по кнопке Вход и
    modalLog.show();//вызываем модальное окно
});

let btnLogPerform = document.getElementById("btnLog"); //переменная для кнопки btnLogPerform из файла modalLog.html
btnLogPerform.addEventListener ("click", function() { //прописываем слушатель на клик по кнопке Вход
     let logLogin = document.getElementById("logLogin"); //переменная для поля по id из файла modalLog.html
     let logPassword = document.getElementById("logPassword"); //переменная для поля по id из файла modalLog.html

    xhr.open("POST", "/login", false);
    const formData = new FormData(); //создаем "тело" для нашего POST запроса, т.е. чтобы передать значения логина и пароля

    formData.append("login", logLogin.value);
    formData.append("password", logPassword.value);

    xhr.send();
    if (xhr.status == 200) {
        console.log("OK");
    } else {
        console.log("=(");
    }

});