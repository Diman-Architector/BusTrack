let loginBtn = document.getElementById("loginBtn");
loginBtn.addEventListener ("click", login);

function login(){
    let xhr = new XMLHttpRequest();
    xhr.open('POST','/',false); //xhr.open - создает запрос, но по сути еще не отправляет, в первом аргументе указывается метод, второй - путь, третий - синхронность или асинхронность
    xhr.send(); // отправляем его
    if (xhr.status != 200) {
        console.log(xhr.status + ' : ' + xhr.statusText); // пример вывода: 404: Not Found
    } else {
    // вывести результат
       console.log(xhr.responseText);} // responseText - текст ответа
}