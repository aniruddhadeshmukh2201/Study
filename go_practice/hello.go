package main

import "fmt"

type Person struct {
	name string
	age  int
}

func (p Person) printer() {
	fmt.Println("Hello my name is", p.name, "and my age is", p.age)
}

func main() {
	fmt.Println("heelo")
	p1 := Person{name: "Aniruddha", age: 27}
	p1.printer()
}

/*
var choice int

what is this syntax..
why is it not similar to c/c++ syntax


what are errors as values... does js have this too?


what is difference between struct and map. in go?

why is syntax different for p Person
*/