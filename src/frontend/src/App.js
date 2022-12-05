import './App.css';
import {useState} from "react";

function App() {

    const [joke, setJoke] = useState();

    loadJoke();

    function loadJoke() {
        fetch(`http://localhost:8080/randomjoke`, {
            "headers": {
                "Accept": "application/json",
            }
        }).then(response => {
            if (!response.ok) {
                throw Error('could not fetch the data for that resource');
            }
            return response.json();
        })
            .then(data => {
                setJoke(data);
                console.log(data);
            }).catch(err => {
        })
    }

  return (
    <div className="App">
      <h2>JokesDB</h2>
      <p>Joke of the day</p>
        {joke.joke? joke.joke : joke.setup + "/n" + joke.delivery}
    </div>
  );
}

export default App;
