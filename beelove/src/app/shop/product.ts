export class Product {
    id: string;
    name: string;
    price: number;
    unit: number;
    pathToImage: string;
    description: string;
    availableUnits: number;
    constructor(id: string, name: string, price: number, unit: number, pathToImage: string, availableUnits:number, description?: string) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.pathToImage = pathToImage;
        this.description = description;
    }
}

