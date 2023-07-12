var countryName="";
var bankId="";
/*나라선택*/
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

/*은행선택*/
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

$.ajax({
    type:'GET',
    url: 'http://grishare.ap-northeast-2.elasticbeanstalk.com/api/exchangeRate/country',
    success : function(data){
        console.log(data);
      },
      error: function(request, status, error){
        alert("잘못된 요청입니다.",error);
      }
  })
$.ajax({
    type:'GET',
    url: 'http://grishare.ap-northeast-2.elasticbeanstalk.com/api/exchangeRate/bank',
    success : function(data){
        console.log(data);
      },
      error: function(request, status, error){
        alert("잘못된 요청입니다.",error);
      }
  })
    

/*선택된 나라와 은행의 환율 받아와야함 */
/*환율계산*/
/*1000원 단위 */
function calculate(){

    $.ajax({
        type: "POST",
        url: `http://grishare.ap-northeast-2.elasticbeanstalk.com/api/exchangeRate/${countryName}/${bankid}`,
        
        contentType: "application/json",
        success: function (data) {
          alert("통신 성공시에만 실행");
          console.log("성공");
          console.log(data);
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

    // let input_value=document.getElementById("money1").value;
    // if(input_value.length>=4){
    //     let output_value=input_value*9.14 //가져온 환율값
    //     console.log(output_value);
    
    //     document.getElementById("money2").value=output_value;
    // }
    
}

