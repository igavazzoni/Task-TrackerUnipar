

if (!localStorage.getItem('task_tracker_data')) { 

  localStorage.setItem('task_tracker_data', JSON.stringify({ users: {} })); 

} 

 

const loginSection = document.getElementById('login-section'); 

const appSection = document.getElementById('app-section'); 

const loginBtn = document.getElementById('login-btn'); 

const logoutBtn = document.getElementById('logout-btn'); 

const usernameInput = document.getElementById('username'); 

const currentUserSpan = document.getElementById('current-user'); 

 

const addTaskBtn = document.getElementById('add-task-btn'); 

const newTaskInput = document.getElementById('new-task'); 

const taskList = document.getElementById('task-list'); 

 

const addHabitBtn = document.getElementById('add-habit-btn'); 

const newHabitInput = document.getElementById('new-habit'); 

const habitList = document.getElementById('habit-list'); 

 

let currentUser = null; 



function getData() { 

  return JSON.parse(localStorage.getItem('task_tracker_data')); 

} 

 

function setData(data) { 

  localStorage.setItem('task_tracker_data', JSON.stringify(data)); 

} 

 

function loadUserData() { 

  const data = getData(); 

  if (!data.users[currentUser]) { 

    data.users[currentUser] = { tasks: [], habits: [] }; 

    setData(data); 

  } 

  currentUserSpan.textContent = currentUser; 

  renderTasks(); 

  renderHabits(); 

} 

 

function renderTasks() { 

  const data = getData(); 

  const tasks = data.users[currentUser].tasks; 

  taskList.innerHTML = ''; 

  tasks.forEach((task, index) => { 

    const li = document.createElement('li'); 

    li.className = 'list-group-item d-flex justify-content-between align-items-center'; 

    li.textContent = task; 

    const deleteBtn = document.createElement('button'); 

    deleteBtn.className = 'btn btn-sm btn-danger'; 

    deleteBtn.textContent = 'Excluir'; 

    deleteBtn.onclick = () => deleteTask(index); 

    li.appendChild(deleteBtn); 

    taskList.appendChild(li); 

  }); 

} 

 

function renderHabits() { 

  const data = getData(); 

  const habits = data.users[currentUser].habits; 

  habitList.innerHTML = ''; 

  habits.forEach((habit, index) => { 

    const li = document.createElement('li'); 

    li.className = 'list-group-item'; 

 

    const habitName = document.createElement('span'); 

    habitName.textContent = habit.name; 

 

    const markBtn = document.createElement('button'); 

    markBtn.className = 'btn btn-sm btn-primary float-end'; 

    markBtn.textContent = 'Marcar'; 

    markBtn.onclick = () => markHabit(index); 

 

    li.appendChild(habitName); 

    li.appendChild(markBtn); 

 

    if (habit.markedDates.length > 0) { 

      const datesList = document.createElement('ul'); 

      datesList.className = 'mt-2'; 

      habit.markedDates.forEach(date => { 

        const dateLi = document.createElement('li'); 

        dateLi.textContent = date; 

        datesList.appendChild(dateLi); 

      }); 

      li.appendChild(datesList); 

    } 

 

    habitList.appendChild(li); 

  }); 

} 

 

function addTask() { 

  const task = newTaskInput.value.trim(); 

  if (task === '') return; 

  const data = getData(); 

  data.users[currentUser].tasks.push(task); 

  setData(data); 

  newTaskInput.value = ''; 

  renderTasks(); 

} 

 

function deleteTask(index) { 

  const data = getData(); 

  data.users[currentUser].tasks.splice(index, 1); 

  setData(data); 

  renderTasks(); 

} 

 

function addHabit() { 

  const habit = newHabitInput.value.trim(); 

  if (habit === '') return; 

  const data = getData(); 

  data.users[currentUser].habits.push({ name: habit, markedDates: [] }); 

  setData(data); 

  newHabitInput.value = ''; 

  renderHabits(); 

} 

 

function markHabit(index) { 

  const data = getData(); 

  const today = new Date().toISOString().split('T')[0]; 

  data.users[currentUser].habits[index].markedDates.push(today); 

  setData(data); 

  renderHabits(); 

} 

 


loginBtn.addEventListener('click', () => { 

  const username = usernameInput.value.trim(); 

  if (username === '') { 

    alert('Por favor, insira um nome de usuário.'); 

    return; 

  } 

  currentUser = username; 

  loadUserData(); 

  loginSection.classList.add('d-none'); 

  appSection.classList.remove('d-none'); 

}); 

 

logoutBtn.addEventListener('click', () => { 

  currentUser = null; 

  loginSection.classList.remove('d-none'); 

  appSection.classList.add('d-none'); 

  usernameInput.value = ''; 

}); 

 

addTaskBtn.addEventListener('click', addTask); 

newTaskInput.addEventListener('keypress', (e) => { 

  if (e.key === 'Enter') addTask(); 

}); 

 

addHabitBtn.addEventListener('click', addHabit); 

newHabitInput.addEventListener('keypress', (e) => { 

  if (e.key === 'Enter') addHabit(); 

}); 

 

