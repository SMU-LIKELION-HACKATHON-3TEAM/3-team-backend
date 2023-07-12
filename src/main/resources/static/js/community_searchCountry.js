$.getJSON("../json/data_searchCountry.json", function(data) {
  $.each(data, function(index, item) {
    //가격비교 API에 추가한다고 하심
    
    // var createdAt = new Date(item.created_at); // "created_at" 값을 Date 객체로 변환
    // var currentTime = new Date(); // 현재 시간
    // var timeDiff = Math.floor((currentTime - createdAt) / (1000 * 60)); // 분 단위로 시간 차이 계산
    // var timeText = timeDiff + "분 전";
    
    // var resultElement = $("<span>").text(timeText).addClass("time");


    // var $postIcon = $('<div>').addClass('postIcon');
    // var $postId = $('<div>').addClass('postId').text(item.userName);

    // var $postContent = $('<textarea>').addClass('postContent').attr('spellcheck', 'false').text(item.content);
    // // title 말고 userName이 들어가는게 맞나?

    // var $views = $('<div>').addClass('views');
    // var $likes = $('<div>').addClass('likes').text(item.like);
    // var $likes_image = $('<div>').addClass('likes_image');

    // var $comment = $('<div>').addClass('comment').text(item.comment);
    // var $comment_image = $('<div>').addClass('comment_image');



    // var $scrap = $('<div>').addClass('scrap').text("스크랩");
    // var $scrap_image = $('<div>').addClass('scrap_image');

    // var $share = $('<div>').addClass('share').text("공유");
    // var $share_image = $('<div>').addClass('share_image');
    // var $report = $('<div>').addClass('report').text("신고");
    

    // var $file_only = $('<div>').addClass('file_only').text(item.file1);
      
      

    // $('#wrap_community').append($postIcon).append(resultElement).append($postId).append($postContent).append($file_only).append($views).append($likes).append($likes_image).append($comment).append($comment_image).append($scrap).append($scrap_image).append($share).append($share_image).append($report).trigger("create");

      
    

      // 네비바 이동
      $('.a-community').click(function() {
        var url = 'http://127.0.0.1:5500/html/community.html';
        window.location.href = url;
    });
      $('.a-exchange').click(function() {
        var url = 'http://127.0.0.1:5500/html/exchangeRate.html';
        window.location.href = url;
    });
      $('.a-price').click(function() {
        var url = 'http://127.0.0.1:5500/html/pricecomparison.html';
        window.location.href = url;
    });
      $('.a-customer').click(function() {
        var url = 'http://127.0.0.1:5500/html/고객지원.html';
        window.location.href = url;
    });





    // url 이거 써서 한번 가져와보기/api/compare/country
    
    $('#wrap_search_country').keyup(function(event) {
        
      if (event.key === 'Enter') {
        var query = $(this).val();
        localStorage.setItem('query', query);
        $('#wrap_search_country').attr('placeholder', query);

        var url = 'http://127.0.0.1:5500/html/community_searchCountry.html';
        window.location.href = url;
      }});






  })

  });
  

var jsonData = {
"code": 200,
"httpStatus": "OK",
"message": "요청에 성공하였습니다.",
"data": [{
  "postId" : "24",
  "title" : "미국 갱스터 피하는 법",
  "content": "게시글게시글게시글내용내용",
  "userName" : "김동현",
  "created_at" : "2023-06-27 16:46:08",
  "views" : "65",
  "like" : "4",
  "comment" : "15",
  "file1" :"image1"
  },
  {
  "postId" : "24",
  "title" : "미국 맛집과 시세 모음",
  "content": "게시글게시글게시글내용내용",
  "userName" : "이은지",
  "created_at" : "2023-06-28 13:15:26",
  "views" : "79",
  "like" : "35",
  "comment" : "19",
  "file1" :"image3"
  },
  {
  "postId" : "24",
  "title" : "미국 맛집과 시세 모음",
  "content": "게시글게시글게시글내용내용",
  "userName" : "홍우기",
  "created_at" : "2023-06-28 13:15:26",
  "views" : "79",
  "like" : "35",
  "comment" : "19",
  "file1" :"image4"
  }]
};

$(document).ready(function() {
  var query = localStorage.getItem('query');
  console.log(query);

  if (query) {
    var url = '/api/compare/country'; // 아직 안올라옴 모든 데이터가 다 들어오니까 query만 걸러서 작업하면 될듯
    $.ajax({
      type: 'GET',
      dataType: 'json',
      url: url,
      data: { json: JSON.stringify(jsonData) },
      success: function(data) {
        console.log('hi');  //아직 안나온듯? 이따 이어서

          var createdAt = new Date(data.created_at);
          var currentTime = new Date();
          var timeDiff = Math.floor((currentTime - createdAt) / (1000 * 60));
          var timeText = timeDiff + "분 전";
          
          var resultElement = $("<span>").text(timeText).addClass("time");
      
      
          var $postIcon = $('<div>').addClass('postIcon');
          var $postId = $('<div>').addClass('postId').text(data.userName);
      
          var $postContent = $('<textarea>').addClass('postContent').attr('spellcheck', 'false').text(data.content);
          // title 말고 userName이 들어가는게 맞나?
      
          var $views = $('<div>').addClass('views');
          var $likes = $('<div>').addClass('likes').text(data.like);
          var $likes_image = $('<div>').addClass('likes_image');
      
          var $comment = $('<div>').addClass('comment').text(data.comment);
          var $comment_image = $('<div>').addClass('comment_image');
      
      
      
          var $scrap = $('<div>').addClass('scrap').text("스크랩");
          var $scrap_image = $('<div>').addClass('scrap_image');
      
          var $share = $('<div>').addClass('share').text("공유");
          var $share_image = $('<div>').addClass('share_image');
          var $report = $('<div>').addClass('report').text("신고");
          
      
          var $file_only = $('<div>').addClass('file_only').text(data.file1);
      

          $('#wrap_community').append($postIcon).append(resultElement).append($postId).append($postContent).append($file_only).append($views).append($likes).append($likes_image).append($comment).append($comment_image).append($scrap).append($scrap_image).append($share).append($share_image).append($report).trigger("create");

           }});
    }
  });

      