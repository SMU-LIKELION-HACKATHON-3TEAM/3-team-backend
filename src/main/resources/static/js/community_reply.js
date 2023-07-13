
  // 네비바 이동
  $('.a-community').click(function() {
    var url = 'http://grishare.ap-northeast-2.elasticbeanstalk.com/html/community.html';
    window.location.href = url;
});
  $('.a-exchange').click(function() {
    var url = 'http://grishare.ap-northeast-2.elasticbeanstalk.com/html/exchangeRate.html';
    window.location.href = url;
});
  $('.a-price').click(function() {
    var url = 'http://grishare.ap-northeast-2.elasticbeanstalk.com/html/pricecomparison.html';
    window.location.href = url;
});
  $('.a-customer').click(function() {
    var url = 'http://grishare.ap-northeast-2.elasticbeanstalk.com/html/고객지원.html';
    window.location.href = url;
});

var postId = localStorage.getItem('postid');


$(document).ready(function() {
    var url = `http://grishare.ap-northeast-2.elasticbeanstalk.com/api/posts/${postId}`;
    $.ajax({
      type: 'GET',
      dataType: 'json',
      url: url,
      success: function(data) {
          console.log("reply connecting");
          console.log(data);
  
          $.each(data, function(index, item) {
            var createdAt = new Date(item.created_at);
            var currentTime = new Date();
            var timeDiff = Math.floor((currentTime - createdAt) / (1000 * 60));
            var timeText = timeDiff + "분 전";
            
            var resultElement = $("<span>").text(timeText).addClass("time") ;
          

            var $postId = $('<div>').addClass('postId').text(item.userName);
        
            var $postContent = $('<textarea>').addClass('postContent').attr('spellcheck', 'false').text(data.content);
            // title 말고 userName이 들어가는게 맞나?
        
            var $views = $('<div>').addClass('views');

            var $likes = $('<div>').addClass('likes').text(item.like_count);
            var $likes_image = $('<img>').attr("id", `likes_image${item.post_id}`).attr("src","../img/icon _heart_.png").addClass('likes_image');
    
        
            var $comments_count = $('<div>').addClass('comment').text(item.comments_count);
            // 이부분
        
        
            var $scrap = $('<div>').addClass('scrap').text("스크랩");
            var $scrap_image = $('<img>').attr("id", `scrap_image${item.post_id}`).attr("src","../img/icon _star outline_.png").addClass('scrap_image');

            var $scrap = $('<div>').addClass('scrap').text("스크랩");
            var $scrap_image = $('<img>').attr("id", `scrap_image${item.post_id}`).attr("src","../img/icon _star outline_.png").addClass('scrap_image');

            var $share = $("<div>").addClass("share").attr("id", `share${item.post_id}`).text("공유");
            var $share_image = $('<div>').attr("id", `share${item.post_id}`).addClass('share_image');

            var $report = $('<div>').attr("id", `report${item.post_id}`).addClass('report').text("신고");
          
        
            var $file_only = $('<div>').addClass('file_only').text(item.file1);
        

            $('#wrap_community').append(resultElement).append($postId).append($postContent).append($file_only).append($views).append($likes).append($likes_image).append($scrap).append($scrap_image).append($share).append($share_image).append($report).trigger("create");
            
            var $comment_box = $('<div>').addClass('comment_box');
            var $comment_post = $('<div>').addClass('comment_post').text('댓글을 작성해 주세요.');
            var $comment_commit = $('<div>').addClass('comment_commit').text('등록');

            $('#wrap_comment_box').append($comment_box);
            $('#wrap_comment_post').append($comment_commit);

            $.each(data.comment_id, function(index, item){
              var $comment_userbox = $('<div>').addClass('comment_userbox');

              var $userName = $('<div>').addClass('userName').text(item.userName);
              var $comment_contents = $('<div>').addClass('comment_contents').text(data.contents);

              $('.commentbox').append($comment_post).append($comment_userbox);
              $('.comment_userbox').appebd($userName).append($comment_contents);
            });
          
          });

          //신고
          let is_clicked_report = false;

          $('.report').click(function(event) {
            
            var id_num = event.target.id.match(/\d+/)[0];
            event.stopPropagation();

            if (!is_clicked_report) {
              var $overlay = $('<div>').addClass('overlay');
              $('body').append($overlay);
              
              var $report_click = $('<div>').addClass('report_click').text("신고하기");

              $(`#report${id_num}`).append($report_click);
              is_clicked_report = true;

            } else {
              $(`#report${id_num}`).find('.report_click').remove();
              $('.overlay').remove();
              is_clicked_report = false;
            }
          });

          $(document).on('click', '.report_click', function() {
            alert("신고되었습니다.");
            var url = 'http://grishare.ap-northeast-2.elasticbeanstalk.com/html/community_reply.html';

            window.location.href = url;
          });


          // 좋아요
          let is_clicked_likes = false;

          $('.likes_image').click(function(event){
            var id_num = event.target.id.match(/\d+/)[0];
            event.stopPropagation();

            if(!is_clicked_likes){
              $(`#likes_image${id_num}`).attr("src", "../img/icon _heart_red.png");
              is_clicked_likes = true;
            }else{
              $(`#likes_image${id_num}`).attr("src", "../img/icon _heart_.png");
              is_clicked_likes = false;
            }
          });

          //스크랩
          let is_clicked_scrap = false;
            
          $('.scrap_image').click(function(event){
            
            var id_num = event.target.id.match(/\d+/)[0];
            event.stopPropagation();

            
            // // 이거 뭘로보내야하지
            // localStorage.setItem('postid', post_id);
            
            if(!is_clicked_scrap){
              $(`#scrap_image${id_num}`).attr("src", "../img/icon _yellow_star outline_.png");
              is_clicked_scrap = true;
            }else{
              $(`#scrap_image${id_num}`).attr("src", "../img/icon _star outline_.png");
              is_clicked_scrap = false;
            }
          });


          // 공유
          let is_clicked_share = false;

          $(".share").click(function (event) {
            
            var id_num = event.target.id.match(/\d+/)[0];
            
            event.stopPropagation();

            if (!is_clicked_share) {
              var $share_image_instagram = $("<img>").attr("src","../img/instagram.png");
              var $share_image_facebook = $("<img>").attr("src", "../img/facebook.png");
              var $share_image_twitter = $("<img>").attr("src", "../img/twitter.png");
              var $share_image_kakao = $("<img>").attr("src", "../img/kakao.png");
              var $share_image_Vector = $("<img>").addClass("Vector").attr("src", "../img/Vector.png");
              var $share_link = $("<div>").addClass("share_link")
              // 링크 추가
              var $share_click = $("<div>").addClass("share_click");
              var $share_copy = $("<div>").addClass("share_copy").text("복사");
              var $share_emoji_box = $("<div>").addClass("share_emoji_box");
              $(`#share${id_num}`).append($share_click);
              $(".share_click").append($share_link);

              $(".share_link").append($share_copy);
              $(`#share${id_num}`).append($share_emoji_box);
              $(".share_emoji_box").append($share_image_instagram);
              $(".share_emoji_box").append($share_image_kakao);
              $(".share_emoji_box").append($share_image_twitter);
              $(".share_emoji_box").append($share_image_facebook);
              $(".share_emoji_box").append($share_image_Vector);
              is_clicked_share = true;
              // var $overlay = $('<div>').addClass('overlay');
              // $('body').append($overlay);

            } else {
              $(".share_click").remove();
              $(".share_emoji_box").remove();
              $(".share_link").remove();
              // $('.overlay').remove();
              is_clicked_share = false;
            }
          });
          $(".share_image").click(function (event) {
            var id_num = event.target.id.match(/\d+/)[0];
            event.stopPropagation();
            $(`#share${id_num}`).click();
          });



           }});
    }
  );

  

