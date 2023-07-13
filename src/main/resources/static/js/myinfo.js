$.ajax({
    type: 'GET',
    url: 'http://grishare.ap-northeast-2.elasticbeanstalk.com/api/mypage',
    success: function(data) {
        

            var $username = $('<div>').addClass('username').text(data.data.username)
            var $email = $('<div>').addClass('email').text(data.data.email)
            var $picture = $('<div>').addClass('picture').text(data.data.picture)
            var $nickname = $('<div>').addClass('nickname').text(data.data.nickname)

            $('#wrap-myid').append($username).append($email).append($picture).append($nickname)
        
    },
    error: function() {
        console.log("실패");
    }
});