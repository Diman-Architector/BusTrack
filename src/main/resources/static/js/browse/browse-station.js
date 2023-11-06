let xhr = new XMLHttpRequest(); //создаем объект xhr в классе XMlHttpRequest. В этом классе у нас содержатся методы для
//работы с сетью Http протоколами

function updateStations (){
    xhr.open("GET", "/api/stations", false); //метод open - конфигурирует запрос который мы собираемся отправить
    xhr.send(); //метод send - отправляет запрос

    let elements = [];
    if (xhr.status == 200) {
        elements = JSON.parse(xhr.responseText);
    }

    let baseUl = document.getElementById("listItemsStation"); //объявляем переменную в которую будет приходить значение
    //названия остановки и передаваться на страницу admin.html

    baseUl.innerHTML = ""; //чистка листа перед новым добавлением

    elements.forEach((e)=> { //forEach принимает параметр e
        let listItem = document.createElement ("li"); //заводим переменную под listItem и указываем название тега li
        //это строка заменяет <li> </li>

        listItem.classList.add("list-group-item"); //указываем класс из bootstrap list-group-item
        //это строка заменяет <li class="list-group-item"> </li>

        let listItemText = document.createTextNode(e.name); //создаем ноду (звено или ячейка) в качестве текста название остановки, которое
        //мы получаем из элемента e Это строка заменяет: Название остановки

        listItem.appendChild(listItemText); //добавляем текстовый элемент  в лист listItem
        //Это строка заменяет <li class="list-group-item">Название остановки</li>

        baseUl.appendChild(listItem); //добавляем на страницу html
    });
}
updateStations();
//<li class="list-group-item">An item</li>