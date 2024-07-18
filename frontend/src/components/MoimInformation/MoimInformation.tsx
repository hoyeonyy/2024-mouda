import * as S from './MoimInformation.style';
import { MoimInfo } from '@_types/index';

interface MoimInformationProps {
  moimInfo: Pick<
    MoimInfo,
    'date' | 'time' | 'place' | 'maxPeople' | 'currentPeople'
  >;
}

export default function MoimInformation(props: MoimInformationProps) {
  const { date, time, place, maxPeople, currentPeople } = props.moimInfo;

  return (
    <div css={S.containerStyle}>
      <h2 css={S.titleStyle}>모임 정보</h2>
      <div css={S.cardStyle}>
        <div css={S.rowStyle}>
          <span>날짜</span>
          <span>{date}</span>
        </div>
        <div css={S.rowStyle}>
          <span>시간</span>
          <span>{time}</span>
        </div>
        <div css={S.rowStyle}>
          <span>장소</span>
          <span>{place}</span>
        </div>
        <div css={S.rowStyle}>
          <span>최대 인원</span>
          <span>{maxPeople}명</span>
        </div>
        <div css={S.rowStyle}>
          <span>현재 참여 인원</span>
          <span>{currentPeople}명</span>
        </div>
      </div>
    </div>
  );
}
