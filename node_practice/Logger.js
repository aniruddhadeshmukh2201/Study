const winston = require('winston');
const path = require('path');

const { combine, timestamp, printf, colorize, errors } = winston.format;

const logFormat = printf(({ level, message, timestamp, stack }) => {
  return `${timestamp} [${level}]: ${stack || message}`;
});

const transports = [];

if (process.env.NODE_ENV === 'dev') {
  transports.push(
    new winston.transports.Console({
      format: combine(
        colorize(),
        timestamp({ format: 'YYYY-MM-DD HH:mm:ss' }),
        logFormat
      )
    })
  );
} else {
  transports.push(
    new winston.transports.File({
      filename: path.join('logs', 'error.log'),
      level: 'error'
    }),
    new winston.transports.File({
      filename: path.join('logs', 'combined.log')
    })
  );
}

const Logger = winston.createLogger({
  level: 'debug', // This can be lowered/raised per env
  format: combine(
    errors({ stack: true }), // captures stack trace for error objects
    timestamp({ format: 'YYYY-MM-DD HH:mm:ss' }),
    logFormat
  ),
  transports
});

module.exports = Logger;
