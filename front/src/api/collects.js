import request from "@/utils/request";

const buildParams = (inputMerchant, customerId) => {
  const params = {};
  if (inputMerchant) params.inputMerchant = inputMerchant; 
  if (customerId) params.customerId = customerId;
  return params;
};
export const addCollect = (customerId, merchantId) => { 
  return request.post(`/collects/addCollect/${customerId}/${merchantId}`)
}

export const likeCollects = (inputMerchant, customerId) => { 
  return request.get(`/collects/likeCollects`,
  {
    params: buildParams(inputMerchant, customerId)
  })
}

export const removeCollect = (customerId, merchantId) => { 
  return request.delete(`/collects/removeCollect/${customerId}/${merchantId}`)
}

export const getCollects = (currentPage, pageSize) => { 
  return request.get(`/collects/getCollects?currentPage=${currentPage}&pageSize=${pageSize}`)
}