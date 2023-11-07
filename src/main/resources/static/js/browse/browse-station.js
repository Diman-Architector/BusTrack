function updateStations (){
    xhr.open("GET", "/api/stations", false); //метод open - конфигурирует запрос который мы собираемся отправить
    xhr.send(); //метод send - отправляет запрос

    let elements = [];
    if (xhr.status == 200) {
        elements = JSON.parse(xhr.responseText);
    }

    baseUl.innerHTML = ""; //чистка листа перед новым добавлением

    elements.forEach((e)=> { //forEach принимает параметр e
        let listItem = document.createElement ("li"); //заводим переменную под listItem и указываем название тега li
        //это строка заменяет <li> </li>

        listItem.addEventListener("click", (elem) => {//передаем еlem-элемент с html страницы для выделения поля для последующего редактирования
          if (selectedStationId == elem.target.getAttribute("value")) {//присваиваем значение атрибута value на тот объект который кликаем
            selectedStationId = null;
            clearSelection(baseUl.children);//функция из init.js для снятия выделения
            buttonActivate(btnEditeStation, btnDeleteStation, true);//btnEditeStation, btnDeleteStation - это id кнопок из admin.html
          } else {
            selectedStationId = elem.target.getAttribute("value");
            clearSelection(baseUl.children);
            elem.target.classList.add("li-selected");
            buttonActivate(btnEditeStation, btnDeleteStation, false);//btnEditeStation, btnDeleteStation - это id кнопок из admin.html
          }
        });
        listItem.classList.add("list-group-item"); //указываем класс из bootstrap list-group-item
        //это строка заменяет <li class="list-group-item"> </li>

        let listItemText = document.createTextNode(e.name); //создаем ноду (звено или ячейка) в качестве текста название остановки, которое
        //мы получаем из элемента e Это строка заменяет: Название остановки

        listItem.appendChild(listItemText); //добавляем текстовый элемент  в лист listItem
        //Это строка заменяет <li class="list-group-item">Название остановки</li>

        listItem.setAttribute("value", e.id); //элемент массива elements c id

        baseUl.appendChild(listItem); //добавляем на страницу html
    });
    document.getElementById("stationName").value = ""; //очищаем поле в модальном окне
    lat=null;
    lng=null;
}
updateStations();
//<li class="list-group-item">An item</li>