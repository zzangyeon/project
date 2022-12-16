
let editor = CKEDITOR.replace('editor' , {filebrowserUploadUrl:'/edit/file'} );

<!--텍스트 저장-->
function saveText(userId){

    let data = editor.getData();
    let title = $('#title').val();

    $.ajax({
        type:"post",
        url:'/edit/text/' + userId,
        data: { 'title' : title, 'data' : data },
        contentType:"application/json; charset=utf-8"
    }).done(res=>{
        alert("성공입니다!!");
    }).fail(error=>{
        alert("실패입니다~~");
    });
}