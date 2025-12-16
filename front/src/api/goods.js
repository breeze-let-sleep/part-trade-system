import request from "@/utils/request";

const buildParams = (inputMerchant, inputPart) => {
  const params = {};
  if (inputMerchant) params.inputMerchant = inputMerchant;
  if (inputPart) params.inputPart = inputPart;
  return params;
};
export const likeSearch = (inputMerchant, inputPart) => {
  return request.get(`/goods/likeGoods`,
  {
    params: buildParams(inputMerchant, inputPart)
  });
}

export const getGoods = (cursor, size) => {
  return request.get(`/goods/getGoods?cursor=${cursor}&size=${size}`);  
}

