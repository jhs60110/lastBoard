$(document).ready(function () {
    // alert("First READY");
    $('#summernote').summernote('disable');

});
$('#collapse').collapse({
    toggle: false
});

/*<![CDATA[*/
function deleteBoard() {
    const id = $("#boardId").val();

    const formHtml = `
          <form id="deleteForm" action="/boards" method="post">
             <input type="hidden" name="_method" value="DELETE"/>
            <input type="hidden" value="${id}" name="boardId"/>
          </form>
      `;
    const doc = new DOMParser().parseFromString(formHtml, 'text/html');
    const form = doc.body.firstChild;


    if (!confirm('게시글을 삭제합니다.')) {
        return false;
    }

    document.body.append(form);
    document.getElementById('deleteForm').submit();
}

/*]]>*/