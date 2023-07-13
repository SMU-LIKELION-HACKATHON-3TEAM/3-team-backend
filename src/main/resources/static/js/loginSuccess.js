// var loggedIn = sessionStorage.getItem('loggedIn');
// if (loggedIn === 'true') {
//     // 로그인된 상태
//     console.log('로그인되었습니다');
//     window.onload = function() {
//     // 이미지 경로
//     var imagePath = "../img/loginLogo.png";
//     var imagePath2 = "../img/logoutLogo.png";

//     // 이미지로 대체할 링크 생성
//     var loginImage = '<img src="' + imagePath + '" alt="로그인">';
//     var logoutImage = '<img src="' + imagePath2 + '" alt="로그인">';
//     // 로그인 로고로 대체
//     $('.login_logo').html(loginImage);
//     $('.logout_logo').html(logoutImage);
//     console.log("아이콘 변경 완료");
//     //로그인 href 마이페이지로 바꾸기 
//     $('.login_logo').prop('href', '../html/mypage.html')
//     //로그아웃 누르면 세션아이디삭제되도록
//     $(".logoutLogo").on('click', function () {
//         loggedIn = 'false';
//         history.back();
//         console.log(loggedIn);
//         location.href='../html/mainpage.html';
//     });
    
// };
//     // });
// } else {
//     // 로그인되지 않은 상태
//     console.log('로그인되지 않았습니다.');
// }
var loggedIn = sessionStorage.getItem('loggedIn');
// document.addEventListener("DOMContentLoaded", function(){
    if (loggedIn === 'true') {
        // 로그인된 상태
        console.log('로그인되었습니다');
        // window.onload = function() {
        // 이미지 경로
        var imagePath = "../img/loginLogo.png";
        var imagePath2 = "../img/logoutLogo.png";
    
        // 이미지로 대체할 링크 생성
        var loginImage = '<img src="' + imagePath + '" alt="로그인">';
        var logoutImage = '<img src="' + imagePath2 + '" alt="로그인">';
        // 로그인 로고로 대체
        $('.login_logo').html(loginImage);
        $('.logout_logo').html(logoutImage);
        console.log("아이콘 변경 완료");
        //로그인 href 마이페이지로 바꾸기 
        $('.login_logo').prop('href', '../html/mypage.html')
        $('.logout_logo').prop('href', '../html/mainpage.html');
        console.log("주소 변경 완료");
        //로그아웃 누르면 세션아이디삭제되도록
        $(".logout_logo").on('click', function () {
            setTimeout(function() {
                $('.login_logo').html("로그인");
                $('.login_logo').prop('href', '../html/login.html');
                $('.logout_logo').html("회원가입");
                $('.logout_logo').prop('href', '../html/signUp.html');
                console.log("아이콘, 주소 원래대로 변경");
              }, 3000);
            loggedIn='false';
            console.log(loggedIn);
        });
        
    // };
        // });
    } else {
        // 로그인되지 않은 상태
        console.log('로그인되지 않았습니다.');
    }
//    });
   