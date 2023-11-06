function updateRoutes (){
    xhr.open("GET", "/api/routes", false); //метод open - конфигурирует запрос который мы собираемся отправить
    xhr.send(); //метод send - отправляет запрос

    let elements = [];
    if (xhr.status == 200) {
        elements = JSON.parse(xhr.responseText);
    }

    let baseUl = document.getElementById("listItemsRoute"); //объявляем переменную в которую будет приходить значение
    //названия маршрутов и передаваться на страницу admin.html

    baseUl.innerHTML = ""; //чистка листа перед новым добавлением

    elements.forEach((e)=> { //forEach принимает параметр e
        let listItem = document.createElement ("li"); //заводим переменную под listItem и указываем название тега li
        //это строка заменяет <li> </li>

        listItem.classList.add("list-group-item"); //указываем класс из bootstrap list-group-item
        //это строка заменяет <li class="list-group-item"> </li>

        let listItemText = document.createTextNode(e.num); //создаем ноду (звено или ячейка) в качестве текста название маршрута, которое
        //мы получаем из элемента e Это строка заменяет: Название маршрута

        listItem.appendChild(listItemText); //добавляем текстовый элемент  в лист listItem
        //Это строка заменяет <li class="list-group-item">Название маршрута</li>

        baseUl.appendChild(listItem); //добавляем на страницу html
    });
}
updateRoutes();

//<li class="list-group-item">An item</li>