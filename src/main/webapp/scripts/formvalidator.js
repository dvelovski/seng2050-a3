/* base test functions */
let notBlank = new BaseTester();

class BaseTester{
    constructor(tFunc, eMsg){
        this.testFunction = tFunc;
        this.errorMessage = eMsg;
    }
    test(){
        return this.testFunction.call();
    }
}