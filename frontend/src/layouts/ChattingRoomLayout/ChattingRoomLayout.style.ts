import { Theme, css } from '@emotion/react';

export const layoutStyle = css`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100vh;
`;

export const headerCenter = ({ theme }: { theme: Theme }) => css`
  ${theme.typography.b1}
  overflow: hidden;

  max-width: 15rem;
  max-height: 2rem;

  text-align: center;
  text-overflow: ellipsis;
  white-space: pre;
`;
