var loggedIn = sessionStorage.getItem('loggedIn');
if (loggedIn === 'true') {
    // 로그인된 상태
    console.log('로그인되었습니다');
    window.load = function() {
    // 이미지 경로
    var imagePath = "../img/loginLogo.png";
    var imagePath2 = "../img/logoutLogo.png";

    // 이미지로 대체할 링크 생성
    var loginImage = '<img src="' + imagePath + '" alt="로그인">';
    var logoutImage = '<img src="' + imagePath2 + '" alt="로그인">';
    // 로그인 로고로 대체
    $('.login_logo').html(loginImage);
    $('.logout_logo').html(logoutImage);
    //로그인 href 마이페이지로 바꾸기 
    $('.login_logo').prop('href', '../html/mypage.html')
    //로그아웃 누르면 세션아이디삭제되도록
    $(".logoutLogo").on('click', function () {
        loggedIn = 'false';
        history.back();
        console.log(loggedIn);
        location.href='../html/mainpage.html';
    });
    
};
    // });
} else {
    // 로그인되지 않은 상태
    console.log('로그인되지 않았습니다.');
}