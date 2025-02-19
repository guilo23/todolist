const url = "http://localhost:8080/v1/task/user/1";

function hiddemLoader(){
    document.getElementById('loading').style.display = 'none';
}

function show(tasks){
    let tab = `<thead>
        <th scope="col">#</th>
        <th scope="col">Description</th>
        <th scope="col">Username</th>
        <th scope="col">user id</th>
    </thead>`;

    for(let task of tasks){
        tab += `<tr>
            <td scope="row">${task.ids}</td>
            <td>${task.description}</td>
            <td>${task.user.username}</td>
            <td>${task.user.ids}</td>
        </tr>`;
        console.log(task);
    }
    document.getElementById('tasks').innerHTML = tab;
}
async function getTasks(url){
    const response = await fetch(url,{method : "GET"});
    const data = await response.json();
    tasks = data;
    console.log(tasks);
        if(response)
            hiddemLoader();
    show(data);
}
getTasks(url);

