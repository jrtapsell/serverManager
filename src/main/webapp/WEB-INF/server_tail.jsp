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
    <h3 class="mdl-dialog__title">Create server</h3>
    <form class="mdl-cell--12-col">
    <div class="mdl-dialog__content">
        <div class="mdl-textfield mdl-js-textfield mdl-cell--12-col">
            <input class="mdl-textfield__input" type="text" id="name" required>
            <label class="mdl-textfield__label" for="name">Name</label>
            <span class="mdl-textfield__error">Field required</span>
        </div>
        <div class="mdl-textfield mdl-js-textfield mdl-cell--12-col">
            <input class="mdl-textfield__input" type="text" id="ip" pattern="[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}" required>
            <label class="mdl-textfield__label" for="ip">IP</label>
            <span class="mdl-textfield__error">Must be in the form X.X.X.X</span>
        </div>
        <div class="mdl-textfield mdl-js-textfield mdl-cell--12-col">
            <input class="mdl-textfield__input" type="text" id="hostname" required>
            <label class="mdl-textfield__label" for="hostname">Hostname</label>
            <span class="mdl-textfield__error">Field required</span>
        </div>
    </div>
    <div class="mdl-dialog__actions">
        <button type="button" class="mdl-button close" id="cancel">Cancel</button>
        <button type="button" class="mdl-button" id="create">Create</button>
    </div>
    </form>
    <script>
        $("#create").click(function() {
            var data = {};
            data["id"] = uuid.v4();
            data["name"] = $("#name").val();
            data["ip"] = $("#ip").val();
            data["hostname"] = $("#hostname").val();
            $.ajax({
                type:"POST",
                data:JSON.stringify(data),
                success:function() {
                    toast("Server created");
                },
                error:function () {
                    toast("Creation failed");
                },
                contentType: "application/json",
                dataType: 'json'
            });
            dialog.close();
        })
    </script>
</dialog>