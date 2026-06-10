print("hello world!")

# this is a comment


# variables
x = 5
y = "hello"
z = 3.14



# function definition
def fun(name):
    print("comming from fun", name)

def fun2(z):
    print("coming from fun 2 ", x, y, z)
    return z

def fun3():
    return fun2(20)
# function call
fun("intro.py")
print(fun2(10))
# printing stuff

print("hi")
print("hi ", "hello")
print("hi " ,x)
print(
    "hi ", x
)


for i in range(10):
    print(i)


print(list(range(10, 20)))