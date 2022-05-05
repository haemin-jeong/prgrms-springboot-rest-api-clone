import {SummaryItem} from "./SummaryItem";
import React, {useState} from "react";

export function Summary({items = [], onOrderSubmit, onRemovePart}) {
    const totalPrice = items.reduce((prev, curr) => prev + (curr.price * curr.quantity), 0);
    const [order, setOrder] = useState({
        email: "", address: "", zipcode: ""
    });
    const handleEmailInputChanged = (e) => setOrder({...order, email: e.target.value});
    const handleAddressInputChanged = (e) => setOrder( {...order, address: e.target.value});
    const handlePostcodeInputChanged = (e) => setOrder({...order, zipcode: e.target.value});
    const handleSubmit = e => {
        if (order.address === "" || order.email === "" || order.zipcode === "") {
            alert("입력값을 확인해주세요!");
        } else {
            onOrderSubmit(order);
        }
    }
    return (
        <>
            <div>
                <h5 className="m-0 p-0"><b>견적카트</b></h5>
            </div>
            <hr/>
            <div>
                <h5>CPU</h5>
                {items.filter(v => v.category === 'CPU').map(v => <SummaryItem key={v.partId} partId={v.partId} quantity={v.quantity} name={v.name} onRemovePart={onRemovePart}/>)}
            </div>
            <div>
                <h5>GPU</h5>
                {items.filter(v => v.category === 'GPU').map(v => <SummaryItem key={v.partId} partId={v.partId}  quantity={v.quantity} name={v.name} onRemovePart={onRemovePart}/>)}
            </div>
            <div>
                <h5>RAM</h5>
                {items.filter(v => v.category === 'RAM').map(v => <SummaryItem key={v.partId} partId={v.partId} quantity={v.quantity} name={v.name} onRemovePart={onRemovePart}/>)}
            </div>
            <div>
                <h5>Main board</h5>
                {items.filter(v => v.category === 'MAIN_BOARD').map(v => <SummaryItem key={v.partId} partId={v.partId} quantity={v.quantity} name={v.name} onRemovePart={onRemovePart}/>)}
            </div>
            <div>
                <h5>SSD</h5>
                {items.filter(v => v.category === 'SSD').map(v => <SummaryItem key={v.partId} partId={v.partId} quantity={v.quantity} name={v.name} onRemovePart={onRemovePart}/>)}
            </div>
            <form>
                <div className="mb-3">
                    <label htmlFor="email" className="form-label">이메일</label>
                    <input type="email" className="form-control mb-1" value={order.email} onChange={handleEmailInputChanged} id="email"/>
                </div>
                <div className="mb-3">
                    <label htmlFor="address" className="form-label">주소</label>
                    <input type="text" className="form-control mb-1" value={order.address} onChange={handleAddressInputChanged} id="address"/>
                </div>
                <div className="mb-3">
                    <label htmlFor="zipcode" className="form-label">우편번호</label>
                    <input type="text" className="form-control" value={order.zipcode} onChange={handlePostcodeInputChanged} id="zipcode"/>
                </div>
            </form>
            <div className="row pt-2 pb-2 border-top">
                <h5 className="col">총금액</h5>
                <h5 className="col text-end">{totalPrice}원</h5>
            </div>
            <button className="btn btn-dark col-12" onClick={handleSubmit}>결제하기</button>
        </>
    )
}