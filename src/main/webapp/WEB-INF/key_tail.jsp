<%--
  Created by IntelliJ IDEA.
  User: james
  Date: 16/03/17
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Colored FAB button with ripple -->
<dialog class="mdl-dialog">
    <h3 class="mdl-dialog__title">Create key</h3>
    <div class="mdl-dialog__content">
        <div class="mdl-textfield mdl-js-textfield mdl-cell--12-col">
            <input class="mdl-textfield__input" type="text" id="name">
            <label class="mdl-textfield__label" for="name">Name</label>
        </div>
        <div class="mdl-textfield mdl-js-textfield mdl-cell--12-col">
            <input class="mdl-textfield__input" type="text" id="data">
            <label class="mdl-textfield__label" for="data">Data</label>
        </div>
    </div>
    <div class="mdl-dialog__actions">
        <button type="button" class="mdl-button close" id="cancel">Cancel</button>
        <button type="button" class="mdl-button" id="create">Create</button>
    </div>
    <script>
        $("#create").click(function() {
            var data = {};
            data["id"] = uuid.v4();
            data["name"] = $("#name").val();
            data["data"] = $("#data").val();
            $.ajax({
                type:"POST",
                data:JSON.stringify(data),
                success:function() {
                    toast("Key created");
                },
                failure:function () {
                    toast("Creation failed");
                },
                contentType: "application/json",
                dataType: 'json'
            });
            dialog.close();
        })
    </script>
</dialog>