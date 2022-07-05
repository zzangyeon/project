let page = 0;

function getArticleItem(article){
    let item = `
            <div class="item" onclick="location.href='/article/${article.id}'">
                <input class="article-id" type="hidden" value="${article.id}" name="articleId" />                
                <div class="imgDiv">
                    <img class="thumbnail" src="/edit/file2/${article.thumbnailUrl}" onerror="this.src = '/thumbnail/basic.jpg'"/>
                </div>    
                <div class="content">
                    <div class="article-title">
                    <p>${article.title}</p>
                    </div>
                    <div class="description">
                        <p>${article.description}</p>
                    </div>
                    <div class="createdDate">
                        <p>${article.createdDate}</p>
                    </div>
                    <div class="username">
                        <p>by <b>${article.user.username}</b></p>
                    </div>
                </div>
            </div> `;
    return item;
}

// 스크롤 페이징하기
$(window).scroll(() => {
    //console.log("윈도우 scrollTop",$(window).scrollTop());
    //console.log("문서의 높이",$(document).height());
    //console.log("윈도우 높이",$(window).height());

    let checkNum = $(window).scrollTop() - ($(document).height() - $(window).height());
    //console.log(checkNum);

    if(-1< checkNum && checkNum < 1){
        page++;

        $.ajax({
            type:"get",
            url:'/api?page='+page,
            contentType:"application/x-www-form-urlencoded; charset=utf-8",
            dataType:"json"
        }).done(res=>{
            console.log(res)
            res.forEach((article)=>{
                let articleItem = getArticleItem(article);
                $(".articleList-container").append(articleItem);
            })
        }).fail(error=>{
            alert("실패")
        });

    }
});

