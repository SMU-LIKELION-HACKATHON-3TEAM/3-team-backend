var jsonData = {
    "code": 200,
    "httpStatus": "OK",
    "message": "요청에 성공하였습니다.",
    "data":{
            "code": 200,
            "httpStatus": "OK",
            "message": "요청에 성공하였습니다.",
                "data": {
                        "like" : "True"
                }
            }
    };
    
$(document).ready(function() {
    $.ajax({
    type: 'GET',
    dataType: 'json',
    url: '/api/post/{CountryCode}/{postId}/like',
    data : { json: JSON.stringify( jsonData ) },
    success: function(data) {

        var $is_like = $('<div>').addClass('is_like').text(data.like);
        

        $('#' + likes).keyup(function(event) {
            if (event.key === 'click') {
                if($is_like){
                    $('#' + likes).css("color","red");
                }else{
                    $('#' + likes).css("color", "#B3B3B3");
                }
            }});
    
    }});
});
    
