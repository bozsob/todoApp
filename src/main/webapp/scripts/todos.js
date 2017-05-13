$(document).ready(function () {

    $("#addButton").click(function() {
        var newTodo = $("#newTodo").val();
        $.post("/todo", {todo : newTodo}, function(data) {
            console.log("addButton data: " + data);
            $("#todos").empty();
            createDOM(data);
            })
        })

    function createDOM(data) {
        for (var i = 0; i < data.length; i++) {
            var todoId = data[i].id;
            var todoText = data[i].text;
            var p = document.createElement("p");
            p.className = "well well-sm";
            var text = document.createTextNode(todoText);
            p.id = todoId;
            p.appendChild(text);
            document.getElementById("todos").appendChild(p);

            var delButton = document.createElement("button");
            delButton.className = "btn btn-danger";
            delButton.style.float = "right";
            var delButtonText = document.createTextNode("Delete");
            delButton.appendChild(delButtonText);
            p.appendChild(delButton);
            delButton.onclick = delTodo();
            

            var doneButton = document.createElement("button");
            doneButton.className = "btn btn-success";
            doneButton.style.float = "left";
            doneButton.onclick = doneTodo();
            var doneButtonText = document.createTextNode("Done");
            doneButton.appendChild(doneButtonText);
            p.appendChild(doneButton);
        }
    }
    function delTodo() {
        $("button .btn btn-danger").click(function() {
            console.log("del button clicked");
            console.log("$(this).parent().attr(id)" + $(this).parent().attr("id"));
            $(this).parent().css("display", "none");

            $.ajax({
                url: "/todoUpdate",
                type: "GET",
                data: {"id": $(this).parent().attr("id")},
                success: function (data) {
                    for(var i = 0; i < data.length; i++) {
                        var todo = data[i].text;
                        console.log("delTodo: " + todo);
                        $("#todos").text(todo);
                    }
                }
            })
        })
    }

    function doneTodo() {
        $("button .btn btn-success").click(function () {
            console.log("done button clicked");
            var text = this.parentNode.nodeValue;
            console.log(text);
            $.ajax({
                url: "/todoUpdate",
                type: "POST",
                data: {"id": $(this).parent().attr("id")},
                success: function () {
                    console.log("toggle Status happened");
                }
            })
        })
    }
});

function loadTodos(filter) {
    $.get("/todo", {"filter": filter}, function(data) {
        $("#todos").empty();
            for (var i = 0; i < data.length; i++) {
                var todoText = data[i].text;
                console.log("textOfTodo: " + todoText);
                var todoId = data[i].id;
                console.log("todoId: " + todoId);
                var p = document.createElement("p");
                p.className = "well well-sm";
                var text = document.createTextNode(todoText);
                p.id = todoId;
                p.appendChild(text);
                document.getElementById("todos").appendChild(p);

            }
    })
}



