/* 나라 보여주기 */
var jsonLocation = "../json/countryList.json";
$.getJSON(jsonLocation, function(data) {
    const length = data.data.length;

    var i;
    for (i = 0; i < length; i++) {

        const countryName = data.data[i].countryName;
        const code = data.data[i].code;

        let countryList = document.querySelector(".country1");
        let country = document.createElement("option");
        country.setAttribute("value", code);
        country.innerHTML = countryName;
        countryList.appendChild(country);
    }
});

var dropdownC = document.getElementById("country1");

dropdownC.addEventListener("change", function() {

    /* 지역 찾기 */

    selectedOption = dropdownC.value;

    var find1 = "../json/";
    var find2 = ".json";
    var findPath = find1 + selectedOption + find2;

    var jsonLocation = findPath;
    $.getJSON(jsonLocation, function(data) {
        const length = data.data.length;

        var i;
        for (i = 0; i < length; i++) {

            const ADName = data.data[i].ADName;
            const code = data.data[i].code;

            let divisionList = document.querySelector(".division1");
            let division = document.createElement("option");
            division.setAttribute("value", code);
            division.innerHTML = ADName;
            divisionList.appendChild(division);
        }
    });
});

/* 물가 찾기 */

var dropdownD = document.getElementById("division1");

dropdownD.addEventListener("change", function() {

    selectedOption = dropdownD.value;

    var find1 = "../json/";
    var find2 = ".json";
    var findPath = find1 + selectedOption + find2;

    console.log(findPath);

    var jsonLocation = findPath;
    $.getJSON(jsonLocation, function(data) {

        const meal = data.data.meal;
        const transport = data.data.transport;
        const accom = data.data.accom;
        const coffee = data.data.coffee;

        barChartData.datasets[0].data[0] = meal;
        barChartData.datasets[0].data[1] = transport;
        barChartData.datasets[0].data[2] = accom;
        barChartData.datasets[0].data[3] = coffee;
        window.theChart.update();

    });





});

/* 차트 값 */
var barChartData = {
    labels: ["한끼 평균 식사비", "1km당 평균 택시비", "한 잔 평균 커피값", "각종 먹거리"],
    datasets: [{
        label: '나라1',
        backgroundColor: "#F87DC0",
        data: [
            0,
            0,
            0,
            0
        ]
    }, {
        label: '나라2',
        backgroundColor: "#83A07F",
        data: [
            0,
            0,
            0,
            0
        ]
    }, {
        label: '나라3',
        backgroundColor: "#F5D8A0",
        data: [
            0,
            0,
            0,
            0
        ]
    }]
};

window.onload = function() {
    var ctx = $('#chart').get(0).getContext("2d");
    window.theChart = new Chart(ctx, {
        type: 'bar',
        data: barChartData,
        options: {}
    });
}