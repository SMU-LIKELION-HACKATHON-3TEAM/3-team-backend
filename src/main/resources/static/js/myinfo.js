$.ajax({
    type: 'GET',
    url: 'http://grishare.ap-northeast-2.elasticbeanstalk.com/api/mypage',
    success: function() {

        var $username = $('<div>').addClass('username').text(item.username)
        var $email = $('<div>').addClass('email').text(item.email)
        var $picture = $('<div>').addClass('picture').text(item.picture)
        var $nickname = $('<div>').addClass('nickname').text(item.nickname)

        $('#wrap-myid').append($username).append($email).append($picture).append($nickname)

        alert('통신 성공시에만 실행');
        console.log("성공");
    },
    error: function() {
        alert('통신 실패시에만 실행');
        console.log("실패");
    }
});