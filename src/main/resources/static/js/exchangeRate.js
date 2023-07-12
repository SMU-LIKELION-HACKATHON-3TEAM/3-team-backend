// /*나라선택*/
// $.getJSON("../json/er_country.json", function (data) {
//     const length = data.data.countries.length;
//     var i;
//     for (i = 0; i < length; i++) {

//         const showData = () => {

//             const countryCode = data.data.countries[i].countryCode;
//             const countryName = data.data.countries[i].countryName;
//             const country_list=document.getElementById('country2');
//             const newOption=document.createElement('option');
//             newOption.value=countryCode;
//             newOption.innerHTML=countryName;
//             country_list.appendChild(newOption);

//         }
//         showData();
//     }
// });

// /*은행선택--->통신되면 ajax사용*/
// $.getJSON("../json/er_bank.json", function (data) {
//     const length = data.data.banks.length;
//     var i;
//     for (i = 0; i < length; i++) {

//         const showData = () => {

//             const bankCode = data.data.banks[i].bankCode;
//             const bankName = data.data.banks[i].bankName;
//             const bank_list=document.getElementById('banks');
//             const newOption=document.createElement('option');
//             newOption.innerHTML=bankName;
//             newOption.value=bankCode;
//             bank_list.appendChild(newOption);


//         }
//         showData();
//     }
// });
// function calculate(){
//     let input_value=document.getElementById("money1").value;
//       if(input_value.length>=4){
//           let output_value=input_value*9.14 //가져온 환율값
//           console.log(output_value);

//           document.getElementById("money2").value=output_value;
//       }
//   }
/*환율선택 */
// $.getJSON("../json/er_bank.json", function (data) {
    
//         const showData = () => {

//             const er=data.data.exchangeRate;
//             console.log(er);

//         }
//         showData();
    
// });

/*********ajax**************/

/*나라 선택*/
$.ajax({
    type: 'GET',
    url: 'http://grishare.ap-northeast-2.elasticbeanstalk.com/api/exchangeRate/country',
    dataType: 'json',
    success: function (data) {
        $.each(data, function (index, item) {
            var newOp = item.countryName;
            var opValue=item.iso2;
            $("#country2").append("<option value=" + opValue + ">" + newOp + "</option>");

        });
        console.log("나라데이터 가져오기 성공")
    },
    error: function (request, status, error) {
        alert("잘못된 요청입니다.", error);
    }
})


/*은행 선택 */
$.ajax({
    type: 'GET',
    url: 'http://grishare.ap-northeast-2.elasticbeanstalk.com/api/exchangeRate/bank',
    dataType: 'json',
    success: function (data) {
        console.log(data);
        $.each(data, function (index, item) {
            var newOp = item.bank;
            var opValue = item.bankCode;
            $("#banks").append("<option value=" + opValue + ">" + newOp + "</option>");

        });
        console.log("은행데이터 가져오기 성공")
        console.log(bank);
    },
    error: function (request, status, error) {
        alert("잘못된 요청입니다.", error);
    }
})

var countryName = $("#country2 option:selected").val();
console.log(countryName);
var bankid = $("#money2").val();


/*환율 가져오기*/
/*통화코드 구현되면 가져와서 넣기 */
$.ajax({
    type: "GET",
    url: `http://grishare.ap-northeast-2.elasticbeanstalk.com/api/exchangeRate/${countryName}/${bankid}`,
    contentType: "application/json",
    success: function (data) {
        console.log("환율 데이터 가져오기 성공");
        console.log(data);
        /*통화코드 가져온 거 p태그 만들어서 넣기 */

    },
    error: function (request, status, error) {
        alert(
            "code:" +
            request.status +
            "\n" +
            "message:" +
            request.responseText +
            "\n" +
            "error:" +
            error
        );
    },
});

/*계산 */
function calculate() {
    let input_value = document.getElementById("money1").value;
    if (input_value.length >= 4) { //1000원 단위
        let output_value = input_value *9.14 //가져온 환율값
        console.log(output_value);

        document.getElementById("money2").value = output_value;
    }
}



