/*** 회원 정보 ***/
var jsonLocation = "../json/userInfor.json";
$.getJSON(jsonLocation, function(data) {

    const showData = () => {

        const userName = data.data.userName;
        const email = data.data.email;
        const nickName = data.data.nickName;
        const picture = data.data.nickName;

        let wrap_infor = document.querySelector(".wrap_infor");

        /* 이미지 */
        // let img = document.createElement("img");
        // img.src = picture;

        /* 이름 */
        let p_name = document.createElement("p");
        p_name.innerHTML = userName;
        let p_email = document.createElement("p");
        p_email.setAttribute("class", "memEmail");
        p_email.innerHTML = email;
        let p_nickName = document.createElement("p");
        p_nickName.setAttribute("class", "memNick");
        p_nickName.innerHTML = nickName;

        // wrap_infor.appendChild(img); // 없어서 보류
        wrap_infor.appendChild(p_name);
        wrap_infor.appendChild(p_email);
        wrap_infor.appendChild(p_nickName);

    }
    showData();

});

/*** 관심국가 ***/
var jsonLocation = "../json/interestCountry.json";
$.getJSON(jsonLocation, function(data) {
    const length = data.data.length;

    var i;
    for (i = 0; i < length; i++) {

        const showData = () => {

            const countryName = data.data[i].countryName;
            const imgUrl = data.data[i].ImageUrl;

            console.log(countryName);
            /* 이미지 */

            let interestConutry = document.querySelector(".interestConutry");

            let country = document.createElement("div");
            country.setAttribute("class", "country");
            interestConutry.appendChild(country);

            let img = document.createElement("img");
            img.src = imgUrl;

            /* 이름 */
            let p = document.createElement("p");
            p.innerHTML = countryName;

            country.appendChild(img);
            country.appendChild(p);
        }
        showData();
    }
});


/*** 내가 쓴 리뷰 ***/
var jsonLocation = "../json/myReview.json";
$.getJSON(jsonLocation, function(data) {

    var length = data.data.length;

    /* 최신 2개만 조회 */
    var forNum;
    if ((length > 2) || (length == 2))
        forNum = 2;
    else
        forNum = length;

    var i;
    for (i = 0; i < forNum; i++) {
        const showData = () => {
            length = length - 1;
            const countryName = data.data[length].countryInfo.countryName;
            const imgUrl = data.data[length].countryInfo.ImageUrl;
            const contents = data.data[length].contents;
            var createdAt = data.data[length].created_at;

            let review_contents = document.querySelector(".review_contents");

            /* 구조 */
            let review = document.createElement("div");
            review.setAttribute("class", "review");
            review_contents.appendChild(review);

            let review_infor = document.createElement("div");
            review_infor.setAttribute("class", "review_infor");
            review.appendChild(review_infor);

            let review_content = document.createElement("div");
            review_content.setAttribute("class", "review_content");
            review.appendChild(review_content);

            var datePart = createdAt.split(' ')[0];
            var formDate = datePart.replace(/-/g, '.');
            let date = document.createElement("p");
            date.setAttribute("class", "review_date");
            date.innerHTML = formDate;
            review.appendChild(date);

            /* 데이터 넣기 */
            /* infor */
            let imageUrl = document.createElement("img");
            imageUrl.src = imgUrl;

            let counName = document.createElement("p");
            counName.innerHTML = countryName;

            review_infor.appendChild(imageUrl);
            review_infor.appendChild(counName);

            /* content */
            let p_contents = document.createElement("p");
            p_contents.innerHTML = contents;

            review_content.appendChild(p_contents);

        }
        showData();
    }
});

/*** 스크랩 게시물 ***/
var jsonLocation = "../json/clipping.json";
$.getJSON(jsonLocation, function(data) {

    var length = data.data.length;

    /* 최신 2개만 조회 */
    var forNum;
    if ((length > 2) || (length == 2))
        forNum = 2;
    else
        forNum = length;

    var i;
    for (i = 0; i < forNum; i++) {
        const showData = () => {
            length = length - 1;
            const writer = data.data[length].countryInfo.countryName; //수정 필요
            const profileImg = data.data[length].countryInfo.ImageUrl; // 수정 필요
            const imgUrl = data.data[length].countryInfo.ImageUrl; // 수정 필요
            const contents = data.data[length].contents;

            /* 시간 계산 */
            var createdAt = data.data[length].created_at;
            var createdTime = new Date(createdAt);

            var current = new Date(); //현재 시간

            var diff = current - createdTime; // 차이
            var diffM = Math.floor(diff / (1000 * 60)); //분으로 환산

            /* 이미지 */
            let clipping_content = document.querySelector(".clipping_content");

            /* 구조 */
            let clipping = document.createElement("div");
            clipping.setAttribute("class", "clipping");
            clipping_content.appendChild(clipping);

            let post_infor = document.createElement("div");
            post_infor.setAttribute("class", "post_infor");
            clipping.appendChild(post_infor);

            let post_content = document.createElement("div");
            post_content.setAttribute("class", "post_content");
            clipping.appendChild(post_content);

            /* 데이터 넣기 */

            /* infor */
            let profilImgge = document.createElement("img");
            profilImgge.src = profileImg;

            let p_writer = document.createElement("p");
            p_writer.setAttribute("class", "writer");
            p_writer.innerHTML = writer;

            let p_time = document.createElement("p");
            p_time.setAttribute("class", "time");
            p_time.innerHTML = diffM + "분 전";

            post_infor.appendChild(profilImgge);
            post_infor.appendChild(p_writer);
            post_infor.appendChild(p_time);

            /* content */
            let p_contents = document.createElement("p");
            p_contents.innerHTML = contents;

            let img = document.createElement("img");
            img.src = imgUrl;

            post_content.appendChild(p_contents);
            post_content.appendChild(img);
        }
        showData();
    }
});