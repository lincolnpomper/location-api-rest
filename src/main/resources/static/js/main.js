var tHead = '<thead><tr><th>Placa</th><th>POI</th><th>Raio</th><th>Latitude</th><th>Longitude</th><th>Tempo (segundos)</th></tr></thead>';

$(document).ready(function () {

    $("#search-form").submit(function (event) {

        //prevent form submit
        event.preventDefault();

        findVehiclePositionsByPlate();
    });

});

$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/vehicles"
    }).then(function(data) {
        var $selector = $("#selectVehicles");
        data.forEach(function(item){
            $selector.append($("<option />").val(item.plate).text(item.plate));
        });
    });
});

function onchangeVehicles(){
    findVehiclePositionsByPlate();
}


function findVehiclePositionsByPlate() {

    var vehiclePlate = $('#selectVehicles').val();
    var stringStartDate = $('#inputStartDate').val();
    var stringEndDate = $('#inputEndDate').val();

    var url = "/vehicles/" + vehiclePlate + "/locations/" + stringStartDate + "/" + stringEndDate;

     $.ajax({

            url: "http://localhost:8080" + url,
            success: function (response) {

                var trHTML = '';

                $.each(response, function (i, item) {
                    trHTML +=
                    '<tr>' +
                    '<td>' + item.vehicle.plate + '</td>' +
                    '<td>' + item.location.name + '</td>' +
                    '<td>' + item.location.radius + '</td>' +
                    '<td>' + item.location.latitude + '</td>' +
                    '<td>' + item.location.longitude + '</td>' +
                    '<td>' + item.timeSpent + '</td>' +
                    '</tr>';
                });

                $('#tableVehicle').empty();
                $('#tableVehicle').append(tHead + trHTML);
            }
        });
}