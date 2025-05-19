





const useDebounce = (value, delay) => {
    const [debouncedValue, setDebouncedValue ] = useState(value);

    useEffect(() => {
        const timer = setTimeout(() => {
            setDebouncedValue(value);
        }, delay);

        return () => {
            clearInterval(timer);
        }
    }, [value, delay]);


    return debouncedValue;
}



const App = () => {
    const [query, setQuery ] = useState();   
    const debounceQuery = useDebounce(query, 500);


    const handleChange = (e) => {
        setQuery(e.target.value);
    }

    const handleClick = () => {
        console.log("this is the debounced query....". debounceQuery);
    }

    return (
        <div>
            <input onChange={handleChange}/>
            <button onClick={handleClick}>call</button>
        </div>
    );
}



