/**
 * jwt-parse.js  ——  浏览器端 JWT payload 解析
 * 用法：
 *   import { parseJwt } from './jwt-parse.js';
 *   const payload = parseJwt(localStorage.getItem('access_token'));
 */
export function parseJwt(token) {
  if (typeof token !== 'string' || !token) return null;

  try {
    const [, payloadBase64url] = token.split('.');
    if (!payloadBase64url) throw new Error('token 格式错误');

    // base64url -> base64
    const payloadBase64 = payloadBase64url
      .replace(/-/g, '+')
      .replace(/_/g, '/');

    // 浏览器原生 atob 解码
    const json = atob(payloadBase64);
    return JSON.parse(json);
  } catch (e) {
    console.error('[jwt-parse] 解析失败:', e.message);
    return null;
  }
}