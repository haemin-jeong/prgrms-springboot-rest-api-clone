import './App.css'
import 'bootstrap/dist/css/bootstrap.css'
import React, {useEffect, useState} from 'react';
import {ProductList} from "./components/ProductList";
import {Summary} from "./components/Summary";
import axios from "axios";

function App() {
    const [parts, setParts] = useState([]);
    const [items, setItems] = useState([]);
    const handleAddClicked = partId => {
        const part = parts.find(v => v.partId === partId);
        const found = items.find(v => v.partId === partId);
        const updatedItems =
            found ? items.map(v => (v.partId === partId) ? {...v, quantity: v.quantity + 1} : v) : [...items, {
                ...part,
                quantity: 1
            }]
        setItems(updatedItems);
    };

    const handleRemoveClicked = partId => {
        console.log(partId);
        items.forEach(v => {
            if (partId === v.partId) v.quantity--;
        });

        setItems(items.filter(v => v.quantity > 0));
    }

    useEffect(() => {
        axios.get('http://localhost:8080/api/v1/parts?category=NONE')
            .then(v => {
                console.log(v.data);
                setParts(v.data)
            })
    }, [])

    const handleOrderSubmit = (order) => {
        if (items.length === 0) {
            alert("아이템을 추가해주세요!");
        } else {
            axios.post("http://localhost:8080/api/v1/orders", {
                email: order.email,
                address: {
                    address: order.address,
                    zipcode: order.zipcode
                },
                orderParts: items.map(v => ({
                    partId: v.partId,
                    category: v.category,
                    price: v.price * v.quantity,
                    quantity: v.quantity
                }))
            }).then(
                v => {
                    alert("주문이 정상적으로 접수되었습니다.");
                    window.location.reload();
                },
                e => {
                    alert("서버 장애");
                    console.error(e);
                })
        }
    }

    return (
        <div className="container-fluid">
            <div className="row justify-content-center m-4">
                <h1 className="text-center">PC 견적</h1>
            </div>
            <div className="card">
                <div className="row">
                    <div className="col-md-8 mt-4 d-flex flex-column align-items-start p-3 pt-0">
                        <ProductList parts={parts} onAddClick={handleAddClicked}/>
                    </div>
                    <div className="col-md-4 summary p-4">
                        <Summary items={items} onOrderSubmit={handleOrderSubmit} onRemovePart={handleRemoveClicked}/>
                    </div>
                </div>
            </div>
        </div>
);
}

export default App;
