const myModal = new bootstrap.Modal(document.getElementById('modalStation'), {}) //exampleModal берем из кода
//модального окна в admin.html - мы его добавили из bootstrap

let btnCreateStation = document.getElementById("btnCreateStation"); //переменную с id, который мы приписали в html коде
// в admin.html
btnCreateStation.addEventListener ("click", function(){ //слушатель на клик, с получением модального окна
    myModal.show();
    });

function saveStation() {
    let stationName = document.getElementById("stationName").value; //получаем значение внесенное в модальное окно по
    //id которое мы добавили в modalStation.html и функции onclick

    xhr.open("POST", "/api/station", false); //создадим xhr запрос POST на адрес /api/station
    let station = {  //создали объект
        id: null,
        name: stationName
    };
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8"); //указываем content-type, для отправки на
    //сервер читаемую строку в нужном charset

    xhr.send(JSON.stringify(station)); //добавили объект station, JSON.stringify превраить объект в строку и
    //отправит на сервер
    if (xhr.status==200){ //если объект придет
    myModal.hide(); //скроется модальное окно
    updateStations(); //обновится список станций
    }
};