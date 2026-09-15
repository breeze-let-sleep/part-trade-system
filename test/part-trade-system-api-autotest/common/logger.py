import logging
from logging.handlers import RotatingFileHandler

from config.settings import LOG_DIR

_LOGGER = None


def get_logger(name: str = "api-test") -> logging.Logger:
    global _LOGGER
    if _LOGGER is not None:
        return _LOGGER

    LOG_DIR.mkdir(parents=True, exist_ok=True)
    logger = logging.getLogger(name)
    logger.setLevel(logging.INFO)
    logger.propagate = False

    if not logger.handlers:
        formatter = logging.Formatter(
            "%(asctime)s | %(levelname)s | %(name)s | %(message)s",
            datefmt="%Y-%m-%d %H:%M:%S",
        )
        console = logging.StreamHandler()
        console.setFormatter(formatter)
        logger.addHandler(console)

        file_handler = RotatingFileHandler(
            LOG_DIR / "api-test.log",
            maxBytes=5 * 1024 * 1024,
            backupCount=5,
            encoding="utf-8",
        )
        file_handler.setFormatter(formatter)
        logger.addHandler(file_handler)

    _LOGGER = logger
    return logger
