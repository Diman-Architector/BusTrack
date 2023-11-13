function saveStation() {
    let stationName = document.getElementById("stationName").value; //получаем значение внесенное в модальное окно по
    //id которое мы добавили в modalStation.html и функции onclick

    xhr.open("POST", "/api/station", false); //создадим xhr запрос POST на адрес /api/station
    let station = {  //создали объект
        id: selectedStationId,
        name: stationName,
        lat: lat,
        lng: lng
    };
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8"); //указываем content-type, для отправки на
    //сервер читаемую строку в нужном charset

    xhr.send(JSON.stringify(station)); //добавили объект station, JSON.stringify превраить объект в строку и
    //отправит на сервер
    if (xhr.status==200){ //если объект придет
        modalStation.hide(); //скроется модальное окно
        selectedStationId = null; //затускним кнопку, сделаем неактивной
        buttonActivate(btnEditeStation, btnDeleteStation, true); //затускним кнопку, сделаем не активной
        updateStations(); //обновится список станций
    }
}

function deleteStation() {
    if (selectedStationId == null){
        return;
    }


    //TODO: реализовать подтверждение удаления
    xhr.open("DELETE", "/api/station?id="+selectedStationId, false); //создадим xhr запрос DELETE на адрес c
    // на /api/station c delete параметром id той строчки с названием станции
    xhr.send(); //отправит на сервер
    if (xhr.status==200){ //если объект придет
       selectedStationId = null; //затускним кнопку, сделаем неактивной
       buttonActivate(btnEditeStation, btnDeleteStation, true); //затускним кнопку, сделаем не активной
       updateStations(); //обновится список станций
    }
}

function getStation() {
    if (selectedStationId == null){
        return;
    }
    xhr.open("GET", "/api/station?id="+selectedStationId, false); //создадим xhr запрос GET на адрес c
    // на /api/station c GET параметром id той строчки с названием станции
    xhr.send(); //отправит на сервер
    if (xhr.status==200){ //если объект придет
        let station = JSON.parse(xhr.responseText);
        document.getElementById("stationName").value = station.name;
        modalStation.show();
    }
}

btnDeleteStation.addEventListener("click",(e) => {
    modalConfirm.show();
    btnYes.addEventListener("click", (c) => {
        deleteStation();
        modalConfirm.hide();
    })
}); //при прослушивании на клик кнопки удалить
//запустится функция deleteStation

btnCreateStation.addEventListener ("click", function(){ //слушатель на клик, с получением модального окна
    selectedStationId = null;
    buttonActivate(btnEditeStation, btnDeleteStation, true);
    clearSelection(baseUl.children);
    document.getElementById("stationName").value = "";
    lat=null;
    lng=null;
    modalStation.show();
    });

btnEditeStation.addEventListener ("click", function(){ //слушатель на клик, с получением модального окна
    getStation();
    });
btnUpload.addEventListener("click", function() {
     modalUpload.show();
 });

btnUploadPerform.addEventListener("click", function() {
    if (upload.files.length == 0) {
        alert("Выберете файлы"); //TODO: переделать на уведомления
        return;
    }
    let fileData = new FormData();
    fileData.append('file', upload.files[0]);
    fileData.append('type', "station");

    xhr.open("POST", "/api/file", false);
    //xhr.setRequestHeader("Content-Type", "multipart/form-data");
    xhr.send(fileData);

    if (xhr.status == 200) {
        let toastLiveExample = document.getElementById('liveToast');
        let alertBox = document.getElementById('alertBox');
        let toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample);
        alertBox.innerHTML = xhr.responseText;
        toastBootstrap.show();
        modalUpload.hide();
        updateStations();
    } else {
        let toastLiveExample = document.getElementById('liveToast');
        let alertBox = document.getElementById('alertBox');
        let toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample);
        alertBox.innerHTML = xhr.responseText;
        toastBootstrap.show();
        console.log(xhr);
    }
 });


function initMap() { //карта
  const map = new google.maps.Map(document.getElementById("map"), {
    zoom: 14,
    center: { lat: 51.52970826835268, lng: 45.97985254567595 },
  });

  map.addListener("click", (mapsMouseEvent) => {
  //TODO: реагировать
    lat = mapsMouseEvent.latLng.lat();
    lng = mapsMouseEvent.latLng.lng();
  });
}

window.initMap = initMap;