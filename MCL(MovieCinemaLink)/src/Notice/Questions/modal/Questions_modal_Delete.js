// Questions_modal_Delete.js
import React, { useState } from "react";
import { Link } from "react-router-dom";
import Questions_Write from "../Write/Questions_Write";

const Questions_modal_Delete = (props) => {
  // 열기, 닫기, 모달 헤더 텍스트를 부모로부터 받아옴
  const { open, close, deleteQAFunc } = props;
  return (
    // 모달이 열릴때 openModal 클래스가 생성된다.
    <div className={open ? "openModal modal" : "modalf"}>
      {open ? (
        <div className="customStyles">
          <div className="Modal_fullTitle">
            <h4>알림</h4>

            <button className="close_btn" onClick={close}>
              &times;
            </button>
          </div>
          <main>
            <br />
            <br />
            <strong>삭제되었습니다.</strong>
            <br />
            <br />
          </main>
          <br />

          {/* <Link to="/Questions_Questions"> */}
          <button onClick={deleteQAFunc}>확인</button>
          {/* </Link> */}
        </div>
      ) : null}
    </div>
  );
};

export default Questions_modal_Delete;