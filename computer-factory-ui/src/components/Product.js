//props에 argument들을 객체로 전달해준다.
import React from "react";

export function Product(props) {
    const partId = props.partId;
    const name = props.name;
    const category = props.category;
    const price = props.price;
    const handleAddBtnClicked = e => props.onAddClick(partId);

    return (
        <>
            <div className="col-7">
                <div className="row text-muted">{category}</div>
                <div className="row">{name}</div>
            </div>
            <div className="col text-center price">{price}원</div>
            <div className="col text-end action">
                <button onClick={handleAddBtnClicked} className="btn btn-small btn-outline-dark" href="">추가</button>
            </div>
        </>
    )
}