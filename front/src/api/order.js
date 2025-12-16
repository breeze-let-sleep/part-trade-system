import request from "@/utils/request";

const buildParams = (partName, orderId, star, startTime, endTime, currentPage, pageSize) => {
  const params = {};
  if (partName) params.partName = partName;
  if (orderId) params.orderId = orderId;
  if (star) params.star = star;
  if (startTime) params.startTime = startTime;  
  if (endTime) params.endTime = endTime;
  params.currentPage = currentPage;
  params.pageSize = pageSize;
  return params;
};
export const addOrder = (order) => { 
  return request.post('/orders/addOrder', order);
}

export const getPaidMessage = () => { 
  return request.get('/orders/getPaidMessage');
}

export const payOrder = (orderId) => { 
  return request.put(`/orders/payOrder/${orderId}`);
}

export const getProcessingOrders = (currentPage, pageSize) => { 
  return request.get(`/orders/getProcessingOrders?currentPage=${currentPage}&pageSize=${pageSize}`);
}

export const removeOrder = (orderId) => { 
  return request.delete(`/orders/removeOrder/${orderId}`);
}

export const getEndLikeOrders = (partName, orderId, star, startTime, endTime, currentPage, pageSize) => { 
  return request.get(`/orders/getEndLikeOrders`,
  {
    params: buildParams(partName, orderId, star, startTime, endTime, currentPage, pageSize)
  }
  );
}