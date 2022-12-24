// 메인화면 페이지 로드 함수
$(document).ready(function () {
    $('#summernote').summernote({
        height: 400,
        maxHeight: 400
    });
});
/*<![CDATA[*/
function deleteBoardFile() {
    const id = $("#boardFileId").val();
    console.log(id);
    const formHtml = `
          <form id="deleteForm" action="/files" method="post">
             <input type="hidden" name="_method" value="DELETE"/>
            <input type="hidden" value="${id}" name="boardFileId"/>
          </form>
      `;
    const doc = new DOMParser().parseFromString(formHtml, 'text/html');
    const form = doc.body.firstChild;


    if (!confirm('파일을 삭제합니다.')) {
        return false;
    }

    document.body.append(form);
    document.getElementById('deleteForm').submit();
}

/*]]>*/