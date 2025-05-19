import { useEffect } from "react";



const useTrottle = (value, delay) => {
    const [val, setVal ] = useState(value);
    const lastExecuted = useRef();

    useEffect(() => {
        let now = Date.now();

        if(now - lastExecuted.current >= delay) {
            setVal(value);
            lastExecuted.current = now;
        }
    }, [value, delay]);

    return val;
}



const App = () => {
    const [value, SetValue]  = useState("");
    const trottleValue  = useTrottle();

    const handleChange = (e) => {
        SetValue(e.target.value);
    }

    const handleClick = () => {
        console.log("-----handleclick----", trottleValue );
    }

    return (
        <div>
            <input onChange={handleChange}/>
            <button onClick={handleClick}>call</button>
        </div>
    );
}