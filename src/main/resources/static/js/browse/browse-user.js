function updateUsers () {
    xhr.open("GET", "/api/users", false); //метод open - конфигурирует запрос который мы собираемся отправить
    xhr.send(); //метод send - отправляет запрос

    let elements = [];
    if (xhr.status == 200) {
        elements = JSON.parse(xhr.responseText);
    }

    let baseUl = document.getElementById("listItemsUser"); //объявляем переменную в которую будет приходить значение
    //названия пользователей передаваться на страницу admin.html

    baseUl.innerHTML = ""; //чистка листа перед новым добавлением

    elements.forEach((e)=> { //forEach принимает параметр e
        let listItem = document.createElement ("li"); //заводим переменную под listItem и указываем название тега li
        //это строка заменяет <li> </li>

        listItem.classList.add("list-group-item"); //указываем класс из bootstrap list-group-item
        //это строка заменяет <li class="list-group-item"> </li>

        let listItemText = document.createTextNode(e.login); //создаем ноду (звено или ячейка) в качестве текста название пользователя, которое
        //мы получаем из элемента e Это строка заменяет: Название пользователя

        listItem.appendChild(listItemText); //добавляем текстовый элемент  в лист listItem
        //Это строка заменяет <li class="list-group-item">Название пользователя</li>

        baseUl.appendChild(listItem); //добавляем на страницу html
    });
}
updateUsers();

//<li class="list-group-item">An item</li>