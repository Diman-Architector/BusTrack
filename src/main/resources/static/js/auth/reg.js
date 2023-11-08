let btnReg = document.getElementById("btnReg"); //переменная для кнопки btnReg из файла index.html
btnReg.addEventListener ("click", function() { //прописываем слушатель на клик по кнопке Регистрация и
    modalReg.show();//вызываем модальное окно
});

let btnRegPerform = document.getElementById("btnReg"); //переменная для кнопки btnLogPerform из файла modalReg.html
btnRegPerform.addEventListener ("click", function() { //прописываем слушатель на клик по кнопке Регистрация
     let regLogin = document.getElementById("regLogin"); //переменная для поля по id из файла modalReg.html
     let regPassword = document.getElementById("regPassword"); //переменная для поля по id из файла modalReg.html

     let user = {
        "id": null,
        "login": regLogin.value,
        "password": regPassword.value
     };

    xhr.open("POST", "/api/user", false);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.send(JSON.stringify(user)); //превратим строку JSON
    if (xhr.status == 200) {
        let toastLiveExample = document.getElementById('liveToast');
        let alertBox = document.getElementById('alertBox');
        let toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample);
        alertBox.innerHTML = "Успешная регистрация! Можете авторизоваться.";
        toastBootstrap.show();
        modalReg.hide();
        location.href = xhr.responseUrl;
    } else {
        let toastLiveExample = document.getElementById('liveToast');
        let alertBox = document.getElementById('alertBox');
        let toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample);
        alertBox.innerHTML = "Ошибка! " + xhr.responseText;
        toastBootstrap.show();
    }


});